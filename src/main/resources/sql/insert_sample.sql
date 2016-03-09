set search_path = kaligia;

INSERT INTO kaligia.roles(
            role_id, name, description)
    VALUES (DEFAULT, 'Admin', 'Admin');

	INSERT INTO kaligia.roles(
            role_id, name, description)
    VALUES (DEFAULT, 'Operator', 'Operator');
	
INSERT INTO kaligia.privileges(
            priv_id, name, description)
    VALUES (DEFAULT, 'All', 'All Privileges');

INSERT INTO kaligia.roleprivs(
            role_id, priv_id)
    VALUES (1, 1);
	
INSERT INTO kaligia.users(
            user_id, login_id, firstname, lastname, passwd, status, type, 
            role_id, email, phone, supervisor, start_date, end_date)
    VALUES (DEFAULT, 'kjohar', 'Kaide', 'Johar', '$2a$04$77SkrAxbisnHoZp0CLJUm.YmKqm20WC3S3XAHp2LmBuV0mOudNfXW', 'Active', 'IT', 
	(select role_id from roles where name='Admin'),
	'kbjohar@gmail.com', '813-469-8167', '', now(), '12-31-2020');

	INSERT INTO kaligia.users(
            user_id, login_id, firstname, lastname, passwd, status, type, 
            role_id, email, phone, supervisor, start_date, end_date)
    VALUES (DEFAULT, 'olesia', 'Olesia', 'Gololobova', '', 'Active', 'DS', 
	(select role_id from roles where name='Admin'),
	'ogololobova@kaligiabiosciences.com', '727-471-0850 815', '', now(), '12-31-2020');


INSERT INTO kaligia.site(
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


INSERT INTO kaligia.device(
            device_id, name, type, manufacturer, model, status, creation_date, 
            created_by)
     VALUES (
DEFAULT,
'Laser 830',
'Laser',
'Innovative Photonics Solution',
'I0830MM0350MF',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Spectrometer QE Pro',
'Spectrometer',
'Ocean Optics',
'QE Pro',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Spectrometer Maya 2000Pro',
'Spectrometer',
'Ocean Optics',
'Maya 2000Pro',
'InActive',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Probe 1',
'Probe',
'InPhotonics',
'RPB Laboratory Raman Probe',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Probe 2',
'Probe',
'InPhotonics',
'RPB Laboratory Raman Probe',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'LabJack U3',
'LabJack',
'LabJack',
'U3',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'LabJack U6',
'LabJack',
'LabJack',
'U6',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'L-Shape 00001',
'Tube',
'inPhotonics',
'Tube',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Straight 00002',
'Tube',
'inPhotonics',
'Tube',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Fiber 105 SMA 00001',
'ExcitationFiber',
'Thorlabs',
'FG105LCA',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Fiber 105 SMA 00002',
'CollectionFiber',
'Thorlabs',
'FG105LCA',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Fiber 105 FC/PC to SMA',
'ExcitationFiber',
'Thorlabs',
'Fiber',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into device values (
DEFAULT,
'Fiber 105 Round to Linear',
'CollectionFiber',
'Thorlabs',
'Fiber',
'Active',
now(),
(select user_id from users where login_id='kjohar')
);

insert into devicespec values (
(select device_id from device where name='L-Shape 00001'),
'WorkingDistance',
'5',
null,
null,
'mm',
'N'
);

insert into devicespec values (
(select device_id from device where name='L-Shape 00001'),
'Shape',
'Straight',
null,
null,
'',
'N'
);

insert into devicespec values (
(select device_id from device where name='Straight 00002'),
'WorkingDistance',
'7.5',
null,
null,
'mm',
'N'
);

insert into devicespec values (
(select device_id from device where name='Straight 00002'),
'Shape',
'Straight',
null,
null,
'',
'N'
);

insert into devicespec values (
(select device_id from device where name='Laser 830'),
'Wavelength',
'830',
null,
null,
'nm',
'N'
);


insert into devicespec values (
(select device_id from device where name='Laser 830'),
'Current',
'800',
'0',
'1492',
'mA',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Laser 830'),
'Power',
'232',
'0',
'240',
'mW',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'Slit',
'100',
null,
null,
'um',
'N'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'IntegrationTime',
'15',
'0.08',
'3600',
's',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'ScansToAverage',
'15',
'1',
'100',
'',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'BoxcarWidth',
'1',
'0',
'15',
'pixel',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'ElectricDark',
'ON',
null,
null,
'',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer QE Pro'),
'NonLinearityCorrection',
'OFF',
null,
null,
'',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'Slit',
'100',
null,
null,
'um',
'N'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'IntegrationTime',
'5',
'0.06',
'10',
's',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'ScansToAverage',
'1',
'1',
'100',
'',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'BoxcarWidth',
'5',
'0',
'15',
'pixel',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'ElectricDark',
'ON',
null,
null,
'',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Spectrometer Maya 2000Pro'),
'NonLinearityCorrection',
'OFF',
null,
null,
'',
'Y'
);

INSERT INTO kaligia.endpoint(end_point_id, name, description, type, status, site_id, created_by, creation_date)
    VALUES (DEFAULT, 'Laptop1', 'Lab Test Laptop 1', 'Lab','Active', 1, 2, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 1, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 2, 'QEP00691', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 3, 'MAYA112352', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 4, 'TT236477', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 4, 'TT236643', '', 'Active', 1, now());
    
INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 6, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 7, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 8, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 9, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 10, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 11, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 12, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.deviceinst(device_inst_id, device_id, serial_id, part_no, status, created_by, creation_date)
    VALUES (DEFAULT, 13, '0001', '', 'Active', 1, now());

INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 1, 2, now(), 'IN-VIVO');
    
INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 2, 2, now(), 'IN-VIVO');

INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 4, 2, now(), 'IN-VIVO');

INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 6, 2, now(), 'IN-VIVO');

INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 8, 2, now(), 'IN-VIVO');
    
INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 10, 2, now(), 'IN-VIVO');

INSERT INTO kaligia.endpointdevices(end_point_id, device_inst_id, created_by, creation_date, used_for)
    VALUES (1, 11, 2, now(), 'IN-VIVO');

INSERT INTO kaligia.endpointprocs(end_point_id, procedure_id, status, created_by, creation_date)
    VALUES (1, 1, 'Active', 2, now());


