set search_path = kaligia;

insert into users values (DEFAULT, 'Kaide Johar', 'Admin');
insert into users values (DEFAULT, 'Olesia Gololobova', 'Admin');

INSERT INTO kaligia.site(
            site_id, name, type, address, city, state, country, zip, phone, 
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
'727-471-0850',
now(),
(select user_id from users where name='Kaide Johar')
);



INSERT INTO kaligia.subject(
            subject_id, name, dob, gender, ethnicity, creation_date, created_by)
    VALUES  (DEFAULT, 'Kaide Johar', '02-11-1972', 'Male', 'Asian', now(), (select user_id from users where name='Kaide Johar'));


INSERT INTO kaligia.specimen(
            specimen_id, subject_id, name, type, creation_date, created_by)
    VALUES (
DEFAULT, 
(select subject_id from subject where name='Kaide Johar'),
'Kaide Skin', 
'Skin', 
now(), 
(select user_id from users where name='Kaide Johar')
);


insert into specimenspec values (
(select specimen_id from specimen where name='Kaide Skin'),
'Location',
'Index Finger',
''
);


INSERT INTO kaligia.device(
            device_id, name, type, manufacturer, model, serial_no, status, 
            creation_date, created_by, site_id)
    VALUES (
DEFAULT,
'Laser 830',
'Laser',
'Innovative Photonics Solution',
'I0830MM0350MF',
'',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Spectrometer QE Pro',
'Spectrometer',
'Ocean Optics',
'QE Pro',
'QEP00691',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Spectrometer Maya 2000Pro',
'Spectrometer',
'Ocean Optics',
'Maya 2000Pro',
'MAYA112352',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Probe 1',
'Probe',
'InPhotonics',
'RPB Laboratory Raman Probe',
'TT236477',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Probe 2',
'Probe',
'InPhotonics',
'RPB Laboratory Raman Probe',
'TT236643',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Excitement',
'Excitation Fiber',
'Thorlabs',
'Step-Index Multimode Fiber Optic Patch Cables',
'TP01095713',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Collection 1',
'Collection Fiber',
'Thorlabs',
'Multimode Fiber',
'TP01081322',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Collection 2',
'Collection Fiber',
'Ocean Optics',
'Multimode Fiber',
'EoS-A661006',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'LabJack U3',
'LabJack',
'LabJack',
'U3',
'',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'LabJack U6',
'LabJack',
'LabJack',
'U6',
'',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
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
'Mode',
'Pulse',
null,
null,
'',
'Y'
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


insert into devicespec values (
(select device_id from device where name='Probe 1'),
'WorkingDistance',
'5',
null,
null,
'mm',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Probe 1'),
'TubeType',
'90',
null,
null,
'degree',
'N'
);

insert into devicespec values (
(select device_id from device where name='Probe 2'),
'WorkingDistance',
'5',
null,
null,
'mm',
'Y'
);

insert into devicespec values (
(select device_id from device where name='Probe 2'),
'TubeType',
'90',
null,
null,
'degree',
'N'
);

insert into devicespec values (
(select device_id from device where name='Fiber Collection 1'),
'Type',
'Round-to-Linear',
null,
null,
'',
'N'
);

insert into devicespec values (
(select device_id from device where name='Fiber Collection 1'),
'Diameter',
'105',
null,
null,
'um',
'N'
);

insert into devicespec values (
(select device_id from device where name='Fiber Collection 2'),
'Type',
'Round-to-Linear',
null,
null,
'',
'N'
);

insert into devicespec values (
(select device_id from device where name='Fiber Collection 2'),
'Diameter',
'200',
null,
null,
'um',
'N'
);


INSERT INTO kaligia.testprocedure(
            procedure_id, name, description, type, no_of_segments, status, 
            creation_date, created_by)
    VALUES  (
DEFAULT,
'IN VIVO BLOOD LASER SPECTRA',
'IN VIVO BLOOD LASER SPECTRA INDEX FINGER SKIN',
'IN-VIVO',
1,
'New',
now(),
(select user_id from users where name='Kaide Johar')
);


insert into testdevices values (
(select device_id from device where name='Laser 830'),
(select procedure_id from testprocedure where name='IN VIVO BLOOD LASER SPECTRA')
);
insert into testdevices values (
(select device_id from device where name='Spectrometer QE Pro'),
(select procedure_id from testprocedure where name='IN VIVO BLOOD LASER SPECTRA')
);
insert into testdevices values (
(select device_id from device where name='Probe 1'),
(select procedure_id from testprocedure where name='IN VIVO BLOOD LASER SPECTRA')
);
insert into testdevices values (
(select device_id from device where name='Fiber Excitement'),
(select procedure_id from testprocedure where name='IN VIVO BLOOD LASER SPECTRA')
);
insert into testdevices values (
(select device_id from device where name='Fiber Collection 1'),
(select procedure_id from testprocedure where name='IN VIVO BLOOD LASER SPECTRA')
);

INSERT INTO kaligia.testsegment(
            segment_id, name, description, created_by, creation_date)
    VALUES (DEFAULT, 'Segment 1', 'Segment 1', (select user_id from users where name='Kaide Johar'), now());

INSERT INTO kaligia.testsegmentspec(
            segment_id, device_id, name, value, unit)
    VALUES (
1,
null,
'Delay',
'2',
's'
);

INSERT INTO kaligia.testsegmentspec(
            segment_id, device_id, name, value, unit)
    VALUES (
1,
null,
'Repeat',
'3',
''
);


INSERT INTO kaligia.testsegmentspec(
            segment_id, device_id, name, value, unit)
    VALUES (
1,
(select device_id from device where name='Spectrometer QE Pro'),
'IntegrationTime',
'25',
's'
);

INSERT INTO kaligia.procsegment(
            segment_id, procedure_id, segment_no)
    VALUES (1, 1, 1);
