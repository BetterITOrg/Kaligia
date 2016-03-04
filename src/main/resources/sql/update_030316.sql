set search_path=kaligia;

insert into device values (
DEFAULT,
'L-Shape 00001',
'Tube',
'inPhotonics',
'Tube',
'00001',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Straight 00002',
'Tube',
'inPhotonics',
'Tube',
'00002',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber 105 SMA 00001',
'ExcitationFiber',
'Thorlabs',
'FG105LCA',
'00001',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber 105 SMA 00002',
'CollectionFiber',
'Thorlabs',
'FG105LCA',
'00002',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber 105 FC/PC to SMA',
'ExcitationFiber',
'Thorlabs',
'Fiber',
'',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
);

insert into device values (
DEFAULT,
'Fiber 105 Round to Linear',
'CollectionFiber',
'Thorlabs',
'Fiber',
'',
'Active',
now(),
(select user_id from users where name='Kaide Johar'),
(select site_id from site where name='Kaligia Biosciences')
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

update device set status='InActive' where name='Spectrometer Maya 2000Pro';

delete from  devicespec where device_id =(select device_id from device where name='Laser 830') and name='Mode';
delete from devicespec where device_id=(select device_id from device where name='Probe 1') and name='WorkingDistance';
delete from devicespec where device_id=(select device_id from device where name='Probe 1') and name='TubeType';
delete from devicespec where device_id=(select device_id from device where name='Probe 2') and name='WorkingDistance';
delete from devicespec where device_id=(select device_id from device where name='Probe 2') and name='TubeType';

