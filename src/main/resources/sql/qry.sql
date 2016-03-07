set search_path=kaligia;

select * from device;
select * from devicespec;
select * from users;
--insert into users values (DEFAULT, 'Bin Yang', 'Admin');
select * from subject;
select * from specimen;


select * from testprocedure;
select * from testdevices;
select * from procsegment;
select * from testsegment;
select * from testsegmentspec;
insert into testsegmentspec values (2, 2, 'SpectrometerType', 'QEPro', '');
insert into testsegmentspec values (2, 9, 'LabJackType', 'U3', '');

update device set type='ExcitationFiber' where type='Excitation Fiber';
update device set type='CollectionFiber' where type='Collection Fiber';

select * from subject;
select * from subjectlog;
select * from specimen;
select * from testorder;
select * from runorder;
select * from runsegment;
select * from runsegmentlog;

--clean up
delete * from runsegmentlog;
delete * from runsegment;
delete * from runorder;
delete * from testorder;

delete * from subject where subject_id in ();
delete * from specimen where subject_id in ();

delete * from testsegmentspec where segment_id in ();
delete * from testsegment where segment_id in ();
delete * from procsegment where procedure_id in ();
delete * from testdevices where procedure_id in ();
delete * from testprocedure where procedure_id in ();


select * from tmp_testresult where run_id=346;;

SELECT tablename FROM pg_catalog.pg_tables where schemaname='kaligia' order by 1;

select * into tmp_testresult from kaligia_1.tmp_testresult;

