set search_path=kaligia;
ALTER TABLE Specimen DROP CONSTRAINT SpecBelongsToSub;
ALTER TABLE Specimen DROP CONSTRAINT SpecCreatedBy;
ALTER TABLE Device DROP CONSTRAINT DevCreatedBy;
ALTER TABLE DeviceSpec DROP CONSTRAINT DevSpecs;
ALTER TABLE TestCase DROP CONSTRAINT TestCaseCreatedBy;
ALTER TABLE TestDevices DROP CONSTRAINT DevicesToTest;
ALTER TABLE TestDevices DROP CONSTRAINT TestDevices;
ALTER TABLE TestCaseSpec DROP CONSTRAINT TestCaseSpecs;
ALTER TABLE TestRun DROP CONSTRAINT TestCaseRun;
ALTER TABLE TestRun DROP CONSTRAINT TestSpecimen;
ALTER TABLE TestRun DROP CONSTRAINT TestRunBy;
ALTER TABLE TestResult DROP CONSTRAINT TestRunResults;
ALTER TABLE SpecimenSpec DROP CONSTRAINT SpecimenSpecs;
ALTER TABLE Device DROP CONSTRAINT DeviceAtSite;
ALTER TABLE TestRun DROP CONSTRAINT TestAtSite;
ALTER TABLE TestCaseSpec DROP CONSTRAINT TestDeviceSpec;
DROP TABLE IF EXISTS Subject CASCADE;
DROP TABLE IF EXISTS Specimen CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Device CASCADE;
DROP TABLE IF EXISTS DeviceSpec CASCADE;
DROP TABLE IF EXISTS TestCase CASCADE;
DROP TABLE IF EXISTS TestDevices CASCADE;
DROP TABLE IF EXISTS TestCaseSpec CASCADE;
DROP TABLE IF EXISTS TestRun CASCADE;
DROP TABLE IF EXISTS TestResult CASCADE;
DROP TABLE IF EXISTS SpecimenSpec CASCADE;
DROP TABLE IF EXISTS Site CASCADE;
CREATE TABLE Subject (
  subject_id  SERIAL NOT NULL, 
  name       varchar(64) NOT NULL, 
  age        int4, 
  gender     char(8), 
  PRIMARY KEY (subject_id));
CREATE TABLE Specimen (
  specimen_id    SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  type          varchar(16) NOT NULL, 
  subject_id    int4 NOT NULL, 
  creation_date timestamp NOT NULL, 
  created_by    int4 NOT NULL, 
  PRIMARY KEY (specimen_id));
CREATE TABLE Users (
  user_id  SERIAL NOT NULL, 
  name    varchar(64) NOT NULL, 
  role    varchar(16) DEFAULT 'operator' NOT NULL, 
  PRIMARY KEY (user_id));
CREATE TABLE Device (
  device_id      SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  type          varchar(16) NOT NULL, 
  manufacturer  varchar(64), 
  model         varchar(64), 
  serial_no     varchar(64), 
  creation_date timestamp NOT NULL, 
  created_by    int4 NOT NULL, 
  site_id       int4 NOT NULL, 
  PRIMARY KEY (device_id));
CREATE TABLE DeviceSpec (
  device_id int4 NOT NULL, 
  name      varchar(64) NOT NULL, 
  value     varchar(64), 
  min_value varchar(64), 
  max_value varchar(64), 
  unit      varchar(16), 
  Tunable   char(1) DEFAULT 'Y' NOT NULL);
CREATE TABLE TestCase (
  testcase_id    SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  description   varchar(255), 
  type          varchar(16) NOT NULL, 
  creation_date timestamp NOT NULL, 
  created_by    int4 NOT NULL, 
  PRIMARY KEY (testcase_id));
CREATE TABLE TestDevices (
  testcase_id int4 NOT NULL, 
  device_id   int4 NOT NULL);
CREATE TABLE TestCaseSpec (
  testcase_id int4 NOT NULL, 
  device_id   int4, 
  name        varchar(64) NOT NULL, 
  value       varchar(64), 
  unit        varchar(16));
CREATE TABLE TestRun (
  run_id         SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  testcase_id   int4 NOT NULL, 
  specimen_id   int4 NOT NULL, 
  status        varchar(16), 
  start_time    timestamp, 
  end_time      timestamp, 
  creation_date timestamp NOT NULL, 
  run_by        int4 NOT NULL, 
  validity      varchar(16), 
  notes         varchar(2000), 
  site_id       int4 NOT NULL, 
  PRIMARY KEY (run_id));
CREATE TABLE TestResult (
  run_id       int4 NOT NULL, 
  run_no       int4 NOT NULL, 
  wavenumber   int4 NOT NULL, 
  photon_count float4 NOT NULL);
CREATE TABLE SpecimenSpec (
  specimen_id int4 NOT NULL, 
  name        varchar(64) NOT NULL, 
  value       varchar(64), 
  unit        varchar(16));
CREATE TABLE Site (
  site_id  SERIAL NOT NULL, 
  name    varchar(64) NOT NULL, 
  type    varchar(16) NOT NULL, 
  address varchar(255), 
  city    varchar(64), 
  state   varchar(64), 
  country varchar(64), 
  zip     varchar(16), 
  phone   varchar(16), 
  PRIMARY KEY (site_id));
CREATE UNIQUE INDEX Subject_subject_id 
  ON Subject (subject_id);
CREATE UNIQUE INDEX Subject_name 
  ON Subject (name);
CREATE UNIQUE INDEX Specimen_specimen_id 
  ON Specimen (specimen_id);
CREATE UNIQUE INDEX Specimen_name 
  ON Specimen (name);
CREATE UNIQUE INDEX Users_user_id 
  ON Users (user_id);
CREATE UNIQUE INDEX Users_name 
  ON Users (name);
CREATE UNIQUE INDEX Device_device_id 
  ON Device (device_id);
CREATE UNIQUE INDEX Device_name 
  ON Device (name);
CREATE INDEX DeviceSpec_name 
  ON DeviceSpec (name);
CREATE UNIQUE INDEX TestCase_testcase_id 
  ON TestCase (testcase_id);
CREATE UNIQUE INDEX TestCase_name 
  ON TestCase (name);
CREATE UNIQUE INDEX TestRun_run_id 
  ON TestRun (run_id);
CREATE UNIQUE INDEX TestRun_name 
  ON TestRun (name);
CREATE INDEX TestResult_run_id 
  ON TestResult (run_id);
CREATE INDEX SpecimenSpec_name 
  ON SpecimenSpec (name);
CREATE UNIQUE INDEX Site_site_id 
  ON Site (site_id);
CREATE UNIQUE INDEX Site_name 
  ON Site (name);
ALTER TABLE Specimen ADD CONSTRAINT SpecBelongsToSub FOREIGN KEY (subject_id) REFERENCES Subject (subject_id);
ALTER TABLE Specimen ADD CONSTRAINT SpecCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE Device ADD CONSTRAINT DevCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE DeviceSpec ADD CONSTRAINT DevSpecs FOREIGN KEY (device_id) REFERENCES Device (device_id);
ALTER TABLE TestCase ADD CONSTRAINT TestCaseCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE TestDevices ADD CONSTRAINT DevicesToTest FOREIGN KEY (device_id) REFERENCES Device (device_id);
ALTER TABLE TestDevices ADD CONSTRAINT TestDevices FOREIGN KEY (testcase_id) REFERENCES TestCase (testcase_id);
ALTER TABLE TestCaseSpec ADD CONSTRAINT TestCaseSpecs FOREIGN KEY (testcase_id) REFERENCES TestCase (testcase_id);
ALTER TABLE TestRun ADD CONSTRAINT TestCaseRun FOREIGN KEY (testcase_id) REFERENCES TestCase (testcase_id);
ALTER TABLE TestRun ADD CONSTRAINT TestSpecimen FOREIGN KEY (specimen_id) REFERENCES Specimen (specimen_id);
ALTER TABLE TestRun ADD CONSTRAINT TestRunBy FOREIGN KEY (run_by) REFERENCES Users (user_id);
ALTER TABLE TestResult ADD CONSTRAINT TestRunResults FOREIGN KEY (run_id) REFERENCES TestRun (run_id);
ALTER TABLE SpecimenSpec ADD CONSTRAINT SpecimenSpecs FOREIGN KEY (specimen_id) REFERENCES Specimen (specimen_id);
ALTER TABLE Device ADD CONSTRAINT DeviceAtSite FOREIGN KEY (site_id) REFERENCES Site (site_id);
ALTER TABLE TestRun ADD CONSTRAINT TestAtSite FOREIGN KEY (site_id) REFERENCES Site (site_id);
ALTER TABLE TestCaseSpec ADD CONSTRAINT TestDeviceSpec FOREIGN KEY (device_id) REFERENCES Device (device_id);
