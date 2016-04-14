CREATE OR REPLACE FUNCTION kaligia.mergetomaster (
   IN  epschema    CHARACTER VARYING,
   IN  increment   integer DEFAULT 1000000,
   OUT status      CHARACTER VARYING)
   RETURNS varchar
AS
$BODY$

DECLARE
   sch_exists     boolean DEFAULT FALSE;
   sub_exists     integer;
   ep_name        varchar;
   master_ep_id   integer;
   slave_ep_id    integer;
   merge_run_id   integer;
   last_run_id    integer;
   ep_row         kaligia.endpoint%ROWTYPE;
   di_row         kaligia.deviceinst%ROWTYPE;
   epd_row        kaligia.endpointdevices%ROWTYPE;
   sub_row        kaligia.subject%ROWTYPE;
   sl_row         kaligia.subjectlog%ROWTYPE;
   spc_row        kaligia.specimen%ROWTYPE;
   tord_row       kaligia.testorder%ROWTYPE;
   ordd_row       kaligia.orderdetails%ROWTYPE;
   rord_row       kaligia.runorder%ROWTYPE;
   rdev_row       kaligia.rundevices%ROWTYPE;
   rseg_row       kaligia.runsegment%ROWTYPE;
   rsl_row        kaligia.runsegmentlog%ROWTYPE;
   rfl_row        kaligia.flremovedlog%ROWTYPE;
   proc_row       kaligia.testprocedure%ROWTYPE;
   tdev_row       kaligia.testdevices%ROWTYPE;
   tseg_row       kaligia.testsegment%ROWTYPE;
   tss_row        kaligia.testsegmentspec%ROWTYPE;
   pseg_row       kaligia.procsegment%ROWTYPE;
   tps_row        kaligia.testprocedurespecs%ROWTYPE;
   site_row       kaligia.site%ROWTYPE;
   user_row       kaligia.users%ROWTYPE;
   r              record;
   rr             record;
   run_ids        integer [];
   rid            integer;
   sid            integer;
   spc_id         integer;
   proc_id        integer;
   tsg_id		  integer;
   siteid         integer;
   uid            integer;
   user_ids       integer [];
   site_ids		  integer [];
   
BEGIN
   SELECT 1
     INTO sch_exists
     FROM pg_catalog.pg_namespace
    WHERE nspname = epSchema;

   IF NOT found
   THEN
      status := 'Failure: Schema Not Found: ' || epSchema;
      RETURN;
   END IF;

   -- check for users
   FOR r IN EXECUTE format ('select * from %1$s.users;', epSchema)
   LOOP
      SELECT user_id
        INTO uid
        FROM kaligia.users
       WHERE login_id = r.login_id;

      IF (uid IS NULL)
      THEN
         -- load users
         user_row := r;
         uid := user_row.user_id;
         user_row.user_id := user_row.user_id + increment;

         INSERT INTO kaligia.users
            SELECT user_row.*;

         RAISE NOTICE 'Inserted User ID: %', user_row.user_id;
         user_ids[uid] := user_row.user_id;
      ELSE
         user_ids[r.user_id] := uid;
      END IF;
   END LOOP;

	-- check for site
   for r in EXECUTE format ('select * from %1$s.site;', epSchema)
   loop
	select site_id into siteid from kaligia.site where name=r.name;
	if(siteid is null) then
	  -- load site
	  site_row := r;
	  siteid := site_row.site_id;
	  site_row.site_id := site_row.site_id + increment;
	  site_row.created_by := user_ids[site_row.created_by];

	  INSERT INTO kaligia.site
		 SELECT site_row.*;

	  RAISE NOTICE 'Inserted Site ID: %', site_row.site_id;
	  site_ids[siteid] := site_row.site_id;
	else
	  site_ids[r.site_id] := siteid;
	end if;
   end loop;

   EXECUTE format ('select name from %1$s.endpoint;', epSchema) INTO ep_name;

   IF (ep_name IS NULL)
   THEN
      status := 'Failure: No End Point Found in Schema: ' || epSchema;
      RETURN;
   END IF;

   EXECUTE format (
             'select end_point_id from kaligia.endpoint e where name = ''%1$s'';',
             ep_name)
      INTO master_ep_id;

   IF (master_ep_id IS NULL)
   THEN
      -- load endpoint
      EXECUTE format ('select * from %1$s.endpoint;', epSchema) INTO ep_row;



      slave_ep_id := ep_row.end_point_id;
      master_ep_id := slave_ep_id + increment;
      ep_row.end_point_id := master_ep_id;
      ep_row.site_id := site_ids[ep_row.site_id];
	  ep_row.created_by := user_ids[ep_row.created_by];

      INSERT INTO kaligia.endpoint
         SELECT ep_row.*;

      RAISE NOTICE 'Inserted End Point ID%', ep_row.end_point_id;

      --load endpoint devices/device inst
      FOR r
         IN EXECUTE format (
                      'select * from %1$s.endpointdevices where end_point_id = %2$s;',
                      epSchema,
                      slave_ep_id)
      LOOP
         EXECUTE format (
                   'select * from %1$s.deviceinst where device_inst_id = %2$s;',
                   epSchema,
                   r.device_inst_id)
            INTO di_row;

         di_row.device_inst_id := di_row.device_inst_id + increment;
		 di_row.created_by := user_ids[di_row.created_by];
		 
         epd_row = r;
		 epd_row.device_inst_id := epd_row.device_inst_id + increment;
         epd_row.end_point_id := epd_row.end_point_id + increment;
		 epd_row.created_by := user_ids[epd_row.created_by];

         INSERT INTO kaligia.deviceinst
            SELECT di_row.* where not exists (select 1 from kaligia.deviceinst where device_inst_id = di_row.device_inst_id);

         INSERT INTO kaligia.endpointdevices
            SELECT epd_row.*;

         RAISE NOTICE 'Inserted EndPoint Device Inst ID %', r.device_inst_id;
      END LOOP;
   ELSE
		execute format('select end_point_id from %1$s.endpoint where name=''%2$s'';', epSchema, ep_name) into slave_ep_id;
    END IF;
   
   EXECUTE format (
             'select max(r.run_id) from kaligia.runorder r where r.end_point_id = %1$s;',
             master_ep_id)
      INTO merge_run_id;

   IF (merge_run_id IS NULL)
   THEN
      merge_run_id := 0;
	ELSE
		merge_run_id := merge_run_id - increment;
   END IF;

   RAISE NOTICE 'Last Merge Run ID %', merge_run_id;

   EXECUTE format (
             'select max(r.run_id)  from %1$s.runorder r where r.end_point_id = %2$s;',
             epSchema,
             slave_ep_id)
      INTO last_run_id;

   IF (last_run_id IS NULL)
   THEN
      last_run_id := 0;
   END IF;

   RAISE NOTICE 'Latest Run ID %', last_run_id;

   IF (last_run_id <= merge_run_id)
   THEN
      --no data to be loaded
      RAISE NOTICE 'No Data to be Loaded from % to %',
      merge_run_id, last_run_id;
      status := 'Success: No Data loaded';
      RETURN;
   END IF;

   -- load run data
   EXECUTE format (
			'select array(select run_id from %1$s.runorder where end_point_id=%2$s and run_id not in (select run_id-%4$s from kaligia.runorder where end_point_id=%3$s));',
             epSchema,
             slave_ep_id,
             master_ep_id,
			 increment)
      INTO run_ids;

 FOREACH rid IN ARRAY run_ids
 LOOP
  RAISE NOTICE 'Run ID: %', rid;

  -- load subject
  -- check if present
  EXECUTE format('select subject_id from kaligia.subject where subject_id= %3$s + (select subject_id from %1$s.testorder where order_id=(select order_id from %1$s.runorder where run_id=%2$s));', epSchema, rid, increment) INTO sub_exists;
  IF (sub_exists IS NULL) THEN
   EXECUTE format('select * from %1$s.subject where subject_id=(select subject_id from %1$s.testorder where order_id=(select order_id from %1$s.runorder where run_id=%2$s));', epSchema, rid) INTO sub_row;
   sub_row.subject_id := sub_row.subject_id + increment;
   sub_row.created_by := user_ids[sub_row.created_by];
   INSERT INTO kaligia.subject SELECT sub_row.*;
   RAISE NOTICE 'Inserted Subject ID: %', sub_row.subject_id;
   sub_exists := sub_row.subject_id;
  END IF;

  -- load test order
  EXECUTE format('select * from %1$s.testorder where order_id=(select order_id from %1$s.runorder where run_id=%2$s);', epSchema, rid) INTO tord_row;
  tord_row.order_id := tord_row.order_id + increment;
  tord_row.subject_id := sub_exists;
  tord_row.site_id := site_ids[tord_row.site_id];
  tord_row.created_by := user_ids[tord_row.created_by];
  INSERT INTO kaligia.testorder SELECT tord_row.*;
  RAISE NOTICE 'Inserted Test Order ID: %', tord_row.order_id;

  -- load order details
  FOR r IN EXECUTE format('select * from %1$s.orderdetails where order_id=(select order_id from %1$s.runorder where run_id=%2$s);', epSchema, rid)
  LOOP
   ordd_row := r;
   ordd_row.order_id := ordd_row.order_id + increment;
   INSERT INTO kaligia.orderdetails SELECT ordd_row.*;
   RAISE NOTICE 'Inserted Order Details for Order ID: %', ordd_row.order_id;
  END LOOP;

  -- load subjectlog
  FOR r IN EXECUTE format('select * from %1$s.subjectlog where order_id=(select order_id from %1$s.runorder where run_id=%2$s);', epSchema, rid)
  LOOP
   sl_row=r;
   sl_row.order_id := sl_row.order_id + increment;
   sl_row.subject_id := sub_exists;
   sl_row.created_by := user_ids[sl_row.created_by];
   INSERT INTO kaligia.subjectlog SELECT sl_row.*;
   RAISE NOTICE 'Inserted Subject Log % for Subject ID: %', sl_row.name, sl_row.subject_id;
  END LOOP;

  -- load specimen
  FOR r IN EXECUTE format('select * from %1$s.specimen where specimen_id in (select specimen_id from %1$s.runorder where run_id=%2$s);', epSchema, rid)
  LOOP
   spc_row := r;
   spc_row.specimen_id := spc_row.specimen_id + increment;
   spc_row.subject_id := spc_row.subject_id + increment;
   spc_row.created_by := user_ids[spc_row.created_by];
   INSERT INTO kaligia.specimen SELECT spc_row.* WHERE NOT EXISTS (SELECT 1 FROM kaligia.specimen WHERE specimen_id=spc_row.specimen_id);
   RAISE NOTICE 'Inserted Specimen ID: %', spc_row.specimen_id;
  END LOOP;

  -- load specimenSpec not needed right now

  EXECUTE format('select * from %1$s.testprocedure where procedure_id=(select procedure_id from %1$s.runorder where run_id=%2$s);', epSchema, rid) INTO proc_row;
  IF EXISTS (SELECT 1 FROM kaligia.testprocedure WHERE name=proc_row.name) THEN
  ELSE
  -- load procedure
  proc_row.procedure_id := proc_row.procedure_id + increment;
  proc_row.created_by := user_ids[proc_row.created_by];
  INSERT INTO kaligia.testprocedure SELECT proc_row.*;
  RAISE NOTICE 'Inserted Procedure ID: %', proc_row.procedure_id;

  -- load test devices
  FOR r IN EXECUTE format('select * from %1$s.testdevices where procedure_id=%2$s;', epSchema, proc_row.procedure_id-increment)
  LOOP
   tdev_row = r;
   tdev_row.procedure_id := proc_row.procedure_id;
   INSERT INTO kaligia.testdevices SELECT tdev_row.*;
  END LOOP;

  -- load test procedure specs
  FOR r IN EXECUTE format('select * from %1$s.testprocedurespecs where procedure_id=%2$s;', epSchema, proc_row.procedure_id-increment)
  LOOP
   tps_row = r;
   tps_row.procedure_id := proc_row.procedure_id;
   INSERT INTO kaligia.testprocedurespecs SELECT tps_row.*;
  END LOOP;

  -- load test procedure segments
  FOR r IN EXECUTE format('select * from %1$s.procsegment where procedure_id=%2$s;', epSchema, proc_row.procedure_id-increment)
  LOOP
   EXECUTE format('select * from %1$s.testsegment where segment_id=%2$s', epSchema, r.segment_id) INTO tseg_row;
    SELECT segment_id into tsg_id FROM kaligia.testsegment WHERE name=tseg_row.name;
    IF (tsg_id is null) THEN
	  -- load testsegment
	  tseg_row.segment_id := tseg_row.segment_id + increment;
	  tseg_row.created_by := user_ids[tseg_row.created_by];
	  INSERT INTO kaligia.testsegment SELECT tseg_row.*;
	  RAISE NOTICE 'Inserted Test Segment ID: %', tseg_row.segment_id;
	  tsg_id := tseg_row.segment_id;
	
	  -- load testsegmentsspec
	  FOR rr IN EXECUTE format('select * from %1$s.testsegmentspec where segment_id=%2$s;', epSchema, r.segment_id)
	  LOOP
	   tss_row := rr;
	   tss_row.segment_id := tseg_row.segment_id;
	   INSERT INTO kaligia.testsegmentspec SELECT tss_row.*;
	  END LOOP;
   END IF;
   
   pseg_row := r;
   pseg_row.procedure_id := proc_row.procedure_id;
   pseg_row.segment_id := tsg_id;
   INSERT INTO kaligia.procsegment SELECT pseg_row.*;
  END LOOP;
  END IF;

  -- load run order
  EXECUTE format('select * from %1$s.runorder where run_id=%2$s;', epSchema, rid) INTO rord_row;
  rord_row.run_id := rord_row.run_id + increment;
  rord_row.order_id := rord_row.order_id + increment;
  rord_row.end_point_id := master_ep_id;
  EXECUTE format('select procedure_id from kaligia.testprocedure where name=(select name from %1$s.testprocedure where procedure_id=%2$s);', epSchema, rord_row.procedure_id) INTO proc_id;
  EXECUTE format('select specimen_id from kaligia.specimen where name=(select name from %1$s.specimen where specimen_id=%2$s);', epSchema, rord_row.specimen_id) INTO spc_id;
  rord_row.procedure_id := proc_id;
  rord_row.specimen_id := spc_id;
  rord_row.site_id := site_ids[rord_row.site_id];
  rord_row.created_by := user_ids[rord_row.created_by];
  INSERT INTO kaligia.runorder SELECT rord_row.*;
  RAISE NOTICE 'Inserted Run Order ID: %', rord_row.run_id;

  -- load rundevices
  FOR r IN EXECUTE format('select * from %1$s.rundevices where run_id=%2$s', epSchema, rid)
  LOOP
   rdev_row := r;
   rdev_row.run_id := rdev_row.run_id + increment;
   rdev_row.device_inst_id := rdev_row.device_inst_id + increment;
   -- check if device inst exists else insert
   IF EXISTS (SELECT 1 FROM kaligia.deviceinst WHERE device_inst_id=rdev_row.device_inst_id) THEN
   ELSE
    -- insert deviceinst
    EXECUTE format('select * from %1$s.deviceinst where device_inst_id = %2$s;', epSchema, rdev_row.device_inst_id-increment) INTO di_row;
    di_row.device_inst_id := di_row.device_inst_id + increment;
	di_row.created_by := user_ids[di_row.created_by];
    INSERT INTO kaligia.deviceinst SELECT di_row.*;
    RAISE NOTICE 'Inserted Device Inst ID: %', di_row.device_inst_id;
   END IF;
   INSERT INTO kaligia.rundevices SELECT rdev_row.*;
   RAISE NOTICE 'Inserted Run Device ID: %', rdev_row.device_inst_id;
  END LOOP;

  -- load runsegment
  FOR r IN EXECUTE format('select * from %1$s.runsegment where run_id=%2$s;', epSchema, rid)
  LOOP
     EXECUTE format('select segment_id from kaligia.testsegment where name=(select name from %1$s.testsegment where segment_id=%2$s);', epSchema, r.segment_id) INTO sid;
      rseg_row := r;
   rseg_row.run_segment_id := rseg_row.run_segment_id + increment;
   rseg_row.run_id := rseg_row.run_id + increment;
   rseg_row.segment_id := sid;
   INSERT INTO kaligia.runsegment SELECT rseg_row.*;

   -- load runsegmentlog
    FOR rr IN EXECUTE format('select * from %1$s.runsegmentlog where run_segment_id=%2$s;', epSchema, rseg_row.run_segment_id-increment)
    LOOP
   rsl_row := rr;
   rsl_row.run_segment_id := rsl_row.run_segment_id + increment;
   INSERT INTO kaligia.runsegmentlog SELECT rsl_row.*;
  END LOOP;

   -- load flremovedlog
  FOR rr IN EXECUTE format('select * from %1$s.flremovedlog where run_segment_id=%2$s;', epSchema, rseg_row.run_segment_id-increment)
    LOOP
    rfl_row := rr;
    rfl_row.run_segment_id := rfl_row.run_segment_id + increment;
    INSERT INTO kaligia.flremovedlog SELECT rfl_row.*;
  END LOOP;

  RAISE NOTICE 'Inserted Run Segment ID: %', rseg_row.run_segment_id;
 END LOOP;

END LOOP;

   status := 'Success';
END;
$BODY$
   LANGUAGE plpgsql
   VOLATILE
   COST 2000;

ALTER FUNCTION kaligia.mergetomaster(CHARACTER VARYING,integer) OWNER TO postgres;