set search_path = kaligia;

insert into site values (
DEFAULT,
'Kaligia Biosciences',
'OFFICE',
'12393 Belcher Rd. Suite 440',
'Largo', 
'FL',
'USA',
'33773',
'727-471-0850'
);

insert into users values (DEFAULT, 'Kaide Johar', 'Admin');
insert into users values (DEFAULT, 'Olesia Gololobova', 'Admin');

insert into subject values (DEFAULT, 'Kaide Johar', 40, 'Male');

insert into specimen values (
DEFAULT, 
'Kaide Skin', 
'Skin', 
(select subject_id from subject where name='Kaide Johar'), 
now(), 
(select user_id from users where name='Kaide Johar')
);

insert into specimenspec values (
(select specimen_id from specimen where name='Kaide Skin'),
'Location',
'Index Finger',
''
);

insert into device values (
DEFAULT,
'Laser 830',
'Laser',
'Innovative Photonics Solution',
'I0830MM0350MF',
'',
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
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Excitement',
'Fiber',
'Thorlabs',
'Step-Index Multimode Fiber Optic Patch Cables',
'TP01095713',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Collection 1',
'Fiber',
'Thorlabs',
'Multimode Fiber',
'TP01081322',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber Collection 2',
'Fiber',
'Ocean Optics',
'Multimode Fiber',
'EoS-A661006',
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

insert into testcase values (
DEFAULT,
'IN VIVO BLOOD LASER SPECTRA',
'IN VIVO BLOOD LASER SPECTRA INDEX FINGER SKIN',
'IN VIVO BLOOD',
now(),
(select user_id from users where name='Kaide Johar')
);

insert into testdevices values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Laser 830')
);
insert into testdevices values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Spectrometer QE Pro')
);
insert into testdevices values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Probe 1')
);
insert into testdevices values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Fiber Excitement')
);
insert into testdevices values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Fiber Collection 1')
);

insert into testcasespec values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
null,
'Delay',
'2',
's'
);

insert into testcasespec values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
null,
'Repeat',
'3',
''
);

insert into testcasespec values (
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select device_id from device where name='Spectrometer QE Pro'),
'IntegrationTime',
'25',
's'
);

insert into testrun values (
DEFAULT,
'IN VIVO BLOOD SPECTRA For Kaide',
(select testcase_id from testcase where name='IN VIVO BLOOD LASER SPECTRA'),
(select specimen_id from specimen where subject_id=(select subject_id from subject where name='Kaide Johar') and type='Skin'),
'Complete',
now(),
now(),
now(),
(select user_id from users where name='Kaide Johar'),
'Valid',
'Sample Data',
(select site_id from site where name='Kaligia Biosciences')
);

COPY testresult FROM 'C:\program files\PostgreSQL\9.5\data\2015-12-23_sample_25s_x2_sub3_S1-R1.txt' (DELIMITER('|'));
