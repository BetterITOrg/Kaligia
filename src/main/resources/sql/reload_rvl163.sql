set search_path = kaligia_3;

INSERT INTO roles(
            role_id, name, description)
    VALUES (DEFAULT, 'Admin', 'Admin');

	INSERT INTO roles(
            role_id, name, description)
    VALUES (DEFAULT, 'Operator', 'Operator');
	
INSERT INTO privileges(
            priv_id, name, description)
    VALUES (DEFAULT, 'All', 'All Privileges');

INSERT INTO roleprivs(
            role_id, priv_id)
    VALUES (1, 1);
	
INSERT INTO users(
            user_id, login_id, firstname, lastname, passwd, status, type, 
            role_id, email, phone, supervisor, start_date, end_date)
    VALUES (DEFAULT, 'kjohar', 'Kaide', 'Johar', '$2a$04$77SkrAxbisnHoZp0CLJUm.YmKqm20WC3S3XAHp2LmBuV0mOudNfXW', 'Active', 'IT', 
	(select role_id from roles where name='Admin'),
	'kbjohar@gmail.com', '813-469-8167', '', now(), '12-31-2020');

	INSERT INTO users(
            user_id, login_id, firstname, lastname, passwd, status, type, 
            role_id, email, phone, supervisor, start_date, end_date)
    VALUES (DEFAULT, 'olesia', 'Olesia', 'Gololobova', '$2a$10$IdBcD89GXNP3uEo0bv90NuMWAlY7Szla.EsUSUI14/uNXeDCdImNu', 'Active', 'DS', 
	(select role_id from roles where name='Admin'),
	'ogololobova@kaligiabiosciences.com', '727-471-0850 815', '', now(), '12-31-2020');


INSERT INTO site(
            site_id, name, type, address, city, state, country, zip, contact, phone, 
            creation_date, created_by)
    VALUES (
DEFAULT,
'Kaligia Biosciences',
'OFFICE',
'12393 Belcher Rd. Suite 440',
'Largo', 
'FL',
'USA',
'33773',
'Fazal Fazlin',
'727-471-0850',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device (
SELECT device_id, name, type, manufacturer, model, status, 
       creation_date, created_by
  FROM kaligia_2.device );
  
INSERT INTO devicespec(
            device_id, name, value, min_value, max_value, unit, tunable)
 (SELECT device_id, name, value, min_value, max_value, unit, tunable
  FROM kaligia_2.devicespec);

INSERT INTO endpoint(end_point_id, name, description, type, status, site_id, created_by, creation_date)
    VALUES (DEFAULT, 'Laptop1', 'Lab Test Laptop 1', 'Lab','Active', 1, 2, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 1, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 2, 'QEP00691', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 3, 'MAYA112352', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 4, 'TT236477', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 4, 'TT236643', '', 'Active', 1, now());
    
INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 6, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 7, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 8, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 9, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 10, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 11, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 12, '0001', '', 'Active', 1, now());

INSERT INTO deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 13, '0001', '', 'Active', 1, now());

INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 1, 2, now(), 'IN-VIVO');
    
INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 2, 2, now(), 'IN-VIVO');

INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 4, 2, now(), 'IN-VIVO');

INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 6, 2, now(), 'IN-VIVO');

INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 8, 2, now(), 'IN-VIVO');
    
INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 10, 2, now(), 'IN-VIVO');

INSERT INTO endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 11, 2, now(), 'IN-VIVO');

insert into testprocedure (select * from kaligia_2.testprocedure );
insert into testsegment (select * from kaligia_2.testsegment );
insert into testsegmentspec (select * from kaligia_2.testsegmentspec );
insert into procsegment (select * from kaligia_2.procsegment );
insert into testdevices (select * from kaligia_2.testdevices );
insert into subject (select * from kaligia_2.subject );
insert into specimen (select * from kaligia_2.specimen );
insert into testorder (select * from kaligia_2.testorder );
insert into subjectlog (select * from kaligia_2.subjectlog );

INSERT INTO kaligia_3.runorder(
            run_id, order_id, type, procedure_id, specimen_id, site_id, end_point_id, 
            status, start_time, end_time, created_date, created_by, run_notes, 
            result_notes)
(SELECT run_id, order_id, type, procedure_id, specimen_id, site_id, 1, status, 
       start_time, end_time, created_date, created_by, run_notes, result_notes
  FROM kaligia_2.runorder);
			
insert into runsegment (select * from kaligia_2.runsegment );
insert into runsegmentlog (select * from kaligia_2.runsegmentlog );

INSERT INTO endpointprocs(
            end_point_id, procedure_id, status, created_by, creation_date)
(select 1, procedure_id, 'Active', created_by, creation_date from testprocedure);

INSERT INTO rundevices(
            run_id, device_inst_id)
(select r.run_id, d.device_inst_id from runorder r, endpointdevices d where d.end_point_id=r.end_point_id order by r.run_id);