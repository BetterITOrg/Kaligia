select 
	u.name users,
	s.name subject,
	sp.name specimen,
	sps.name specName,
	sps.value specValue,
	d.name device,
	ds.name devSpecName,
	ds.value devSpecValue,
	tc.name test,
	tcs.name testSpecName,
	tcs.value testSpecValue,
	tr.name RunName,
	tr.status RunStatus,
	tr.end_time RunTime
from 
	testrun tr,
	testcase tc, 
	testcasespec tcs, 
	testdevices td, 
	device d, 
	devicespec ds,
	specimen sp,
	specimenspec sps,
	subject su,
	users u,
	site s
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and tcs.testcase_id=tc.testcase_id
	and td.testcase_id=tc.testcase_id
	and d.device_id=td.device_id
	and ds.device_id=d.device_id
	and sp.specimen_id=tr.specimen_id
	and sps.specimen_id=sp.specimen_id
	and su.subject_id=sp.subject_id
	and s.site_id=tr.site_id
	and u.user_id=tr.run_by
;

select
	tr.name RunName,
	tr.status RunStatus,
	tr.end_time RunTime,
	tc.name test,
	su.name subject,
	sp.name specName,
	u.name users,
	s.name site
from
	testrun tr,
	testcase tc,
	specimen sp,
	subject su,
	users u,
	site s
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and s.site_id=tr.site_id
	and u.user_id=tr.run_by
	and sp.specimen_id=tr.specimen_id
	and su.subject_id=sp.subject_id
;

select 
	tc.name test,
	d.name,
	d.manufacturer,
	d.model
from
	testrun tr,
	testcase tc,
	testdevices td,
	device d
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and td.testcase_id=tc.testcase_id
	and d.device_id=td.device_id
;


select 
	tc.name test,
	'' device,
	tcs.name testSpecName,
	tcs.value testSpecValue
from
	testrun tr,
	testcase tc,
	testcasespec tcs
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and tcs.testcase_id=tc.testcase_id
	and tcs.device_id is null
union all
select 
	tc.name test,
	d.name device,
	tcs.name testSpecName,
	tcs.value testSpecValue
from
	testrun tr,
	testcase tc,
	testcasespec tcs,
	device d 
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and tcs.testcase_id=tc.testcase_id
	and tcs.device_id is not null
	and d.device_id=tcs.device_id
;

select 
	tc.name test,
	d.name device,
	ds.name param,
	ds.value val,
	ds.unit unit
from
	testrun tr,
	testcase tc,
	testdevices td,
	device d,
	devicespec ds
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and tc.testcase_id=tr.testcase_id
	and td.testcase_id=tc.testcase_id
	and d.device_id=td.device_id
	and ds.device_id=d.device_id
;

select
	tr.name test,
	r.run_no,
	r.wavenumber,
	r.photon_count
from
	testrun tr,
	testresult r
where tr.name='IN VIVO BLOOD SPECTRA For Kaide'
	and r.run_id=tr.run_id
;