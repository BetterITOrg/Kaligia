set search_path=kaligia;

select * from device;
select * from users;
insert into users values (DEFAULT, 'Bin Yang', 'Admin');
select * from subject;
select * from specimen;


select * from testprocedure;
select * from testdevices;
select * from procsegment;
select * from testsegment;
select * from testsegmentspec;

select * from testcasespec;
select * from testrun;
select * from testresult;

delete from testresult;
delete from testrun;
delete from testcasespec;
delete from testdevices;
delete from testcase;






