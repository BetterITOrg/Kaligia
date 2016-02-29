set search_path=kaligia;
ALTER TABLE Specimen DROP CONSTRAINT SpecBelongsToSub;
ALTER TABLE Specimen DROP CONSTRAINT SpecimenCreatedBy;
ALTER TABLE Device DROP CONSTRAINT DevCreatedBy;
ALTER TABLE DeviceSpec DROP CONSTRAINT DevSpecs;
ALTER TABLE TestProcedure DROP CONSTRAINT RunProcedureCreatedBy;
ALTER TABLE TestDevices DROP CONSTRAINT DevicesToTest;
ALTER TABLE SpecimenSpec DROP CONSTRAINT SpecimenSpecs;
ALTER TABLE Device DROP CONSTRAINT DeviceAtSite;
ALTER TABLE TestSegmentSpec DROP CONSTRAINT TestDeviceSpec;
ALTER TABLE SubjectLog DROP CONSTRAINT SubjectVitalStats;
ALTER TABLE SubjectLog DROP CONSTRAINT VitalsTakenBy;
ALTER TABLE ProcSegment DROP CONSTRAINT SegmentsForProcedure;
ALTER TABLE RunOrder DROP CONSTRAINT ProcedureForOrder;
ALTER TABLE RunOrder DROP CONSTRAINT RunAtSite;
ALTER TABLE RunOrder DROP CONSTRAINT RunOrderForSpecimen;
ALTER TABLE RunSegment DROP CONSTRAINT RunOrderSegment;
ALTER TABLE LabLog DROP CONSTRAINT LabForRunOrder;
ALTER TABLE OrderDetails DROP CONSTRAINT OrderDetails;
ALTER TABLE TestOrder DROP CONSTRAINT OrderAtSite;
ALTER TABLE TestOrder DROP CONSTRAINT OrderForSubject;
ALTER TABLE SubjectLog DROP CONSTRAINT SubjectLogForOrder;
ALTER TABLE Subject DROP CONSTRAINT SubjectCreatedBy;
ALTER TABLE RunOrder DROP CONSTRAINT RunOrderCreatedBy;
ALTER TABLE TestDevices DROP CONSTRAINT RunProcedureDevices;
ALTER TABLE RunOrder DROP CONSTRAINT SubOrder;
ALTER TABLE TestOrder DROP CONSTRAINT OrderCreatedBy;
ALTER TABLE Site DROP CONSTRAINT SiteCreatedBy;
ALTER TABLE ProcSegment DROP CONSTRAINT SegmentInProc;
ALTER TABLE TestSegmentSpec DROP CONSTRAINT SegmentForTest;
ALTER TABLE RunSegment DROP CONSTRAINT RunSegment;
ALTER TABLE RunSegmentLog DROP CONSTRAINT LogForRunSegment;
DROP TABLE IF EXISTS Subject CASCADE;
DROP TABLE IF EXISTS Specimen CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Device CASCADE;
DROP TABLE IF EXISTS DeviceSpec CASCADE;
DROP TABLE IF EXISTS TestProcedure CASCADE;
DROP TABLE IF EXISTS TestDevices CASCADE;
DROP TABLE IF EXISTS TestSegmentSpec CASCADE;
DROP TABLE IF EXISTS RunSegment CASCADE;
DROP TABLE IF EXISTS SpecimenSpec CASCADE;
DROP TABLE IF EXISTS Site CASCADE;
DROP TABLE IF EXISTS SubjectLog CASCADE;
DROP TABLE IF EXISTS TestOrder CASCADE;
DROP TABLE IF EXISTS OrderDetails CASCADE;
DROP TABLE IF EXISTS ProcSegment CASCADE;
DROP TABLE IF EXISTS RunOrder CASCADE;
DROP TABLE IF EXISTS LabLog CASCADE;
DROP TABLE IF EXISTS RunSegmentLog CASCADE;
DROP TABLE IF EXISTS TestSegment CASCADE;
CREATE TABLE Subject (
  subject_id     SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  dob           date, 
  gender        char(8), 
  ethnicity     varchar(32), 
  creation_date timestamp, 
  created_by    int4 NOT NULL, 
  PRIMARY KEY (subject_id));
CREATE TABLE Specimen (
  specimen_id    SERIAL NOT NULL, 
  subject_id    int4 NOT NULL, 
  name          varchar(64) NOT NULL, 
  type          varchar(16) NOT NULL, 
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
  status        varchar(16) DEFAULT 'ACTIVE' NOT NULL, 
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
CREATE TABLE TestProcedure (
  procedure_id    SERIAL NOT NULL, 
  name           varchar(64) NOT NULL, 
  description    varchar(255), 
  type           varchar(16) NOT NULL, 
  no_of_segments int4, 
  status         varchar(16) DEFAULT 'ACTIVE' NOT NULL, 
  creation_date  timestamp NOT NULL, 
  created_by     int4 NOT NULL, 
  PRIMARY KEY (procedure_id));
CREATE TABLE TestDevices (
  device_id    int4 NOT NULL, 
  procedure_id int4 NOT NULL);
CREATE TABLE TestSegmentSpec (
  segment_id int4 NOT NULL, 
  device_id  int4, 
  name       varchar(64) NOT NULL, 
  value      varchar(64), 
  unit       varchar(16));
CREATE TABLE RunSegment (
  run_segment_id  SERIAL NOT NULL, 
  segment_id     int4 NOT NULL, 
  run_id         int4 NOT NULL, 
  run_no         int4 NOT NULL, 
  status         varchar(16), 
  start_time     timestamp, 
  end_time       timestamp, 
  PRIMARY KEY (run_segment_id));
CREATE TABLE SpecimenSpec (
  specimen_id int4 NOT NULL, 
  name        varchar(64) NOT NULL, 
  value       varchar(64), 
  unit        varchar(16));
CREATE TABLE Site (
  site_id        SERIAL NOT NULL, 
  name          varchar(64) NOT NULL, 
  type          varchar(16) NOT NULL, 
  address       varchar(255), 
  city          varchar(64), 
  state         varchar(64), 
  country       varchar(64), 
  zip           varchar(16), 
  phone         varchar(16), 
  creation_date timestamp, 
  created_by    int4 NOT NULL, 
  PRIMARY KEY (site_id));
CREATE TABLE SubjectLog (
  subject_id    int4 NOT NULL, 
  order_id      int4 NOT NULL, 
  name          varchar(64) NOT NULL, 
  value         varchar(64), 
  unit          varchar(16), 
  creation_date timestamp, 
  created_by    int4 NOT NULL);
CREATE TABLE TestOrder (
  order_id       SERIAL NOT NULL, 
  order_no      varchar(64) NOT NULL, 
  subject_id    int4, 
  site_id       int4 NOT NULL, 
  description   varchar(255), 
  creation_date timestamp NOT NULL, 
  created_by    int4 NOT NULL, 
  PRIMARY KEY (order_id));
CREATE TABLE OrderDetails (
  order_id int4 NOT NULL, 
  name     varchar(64), 
  value    varchar(64));
CREATE TABLE ProcSegment (
  segment_id   int4 NOT NULL, 
  procedure_id int4 NOT NULL, 
  segment_no   int4 DEFAULT 1 NOT NULL);
CREATE TABLE RunOrder (
  run_id        SERIAL NOT NULL, 
  order_id     int4 NOT NULL, 
  type         varchar(32) NOT NULL, 
  procedure_id int4, 
  specimen_id  int4 NOT NULL, 
  site_id      int4 NOT NULL, 
  status       varchar(16) DEFAULT 'NEW' NOT NULL, 
  start_time   timestamp, 
  end_time     timestamp, 
  created_date timestamp NOT NULL, 
  created_by   int4 NOT NULL, 
  run_notes    varchar(2000), 
  result_notes varchar(2000), 
  PRIMARY KEY (run_id));
CREATE TABLE LabLog (
  run_id int4 NOT NULL, 
  name   varchar(64), 
  value  varchar(64), 
  unit   varchar(16));
CREATE TABLE RunSegmentLog (
  run_segment_id int4 NOT NULL, 
  r_index        int4, 
  wavelength     float8, 
  photon_count   float8);
CREATE TABLE TestSegment (
  segment_id     SERIAL NOT NULL, 
  name          varchar(64), 
  description   varchar(255), 
  created_by    int4, 
  creation_date timestamp, 
  PRIMARY KEY (segment_id));
CREATE UNIQUE INDEX Subject_subject_id 
  ON Subject (subject_id);
CREATE UNIQUE INDEX Subject_name 
  ON Subject (name);
CREATE UNIQUE INDEX Specimen_specimen_id 
  ON Specimen (specimen_id);
CREATE INDEX Specimen_subject_id 
  ON Specimen (subject_id);
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
CREATE UNIQUE INDEX TestProcedure_procedure_id 
  ON TestProcedure (procedure_id);
CREATE UNIQUE INDEX TestProcedure_name 
  ON TestProcedure (name);
CREATE INDEX TestDevices_device_id 
  ON TestDevices (device_id);
CREATE INDEX TestDevices_procedure_id 
  ON TestDevices (procedure_id);
CREATE INDEX TestSegmentSpec_segment_id 
  ON TestSegmentSpec (segment_id);
CREATE UNIQUE INDEX RunSegment_run_segment_id 
  ON RunSegment (run_segment_id);
CREATE INDEX RunSegment_segment_id 
  ON RunSegment (segment_id);
CREATE INDEX RunSegment_run_id 
  ON RunSegment (run_id);
CREATE INDEX SpecimenSpec_specimen_id 
  ON SpecimenSpec (specimen_id);
CREATE INDEX SpecimenSpec_name 
  ON SpecimenSpec (name);
CREATE UNIQUE INDEX Site_site_id 
  ON Site (site_id);
CREATE UNIQUE INDEX Site_name 
  ON Site (name);
CREATE INDEX SubjectLog_subject_id 
  ON SubjectLog (subject_id);
CREATE INDEX SubjectLog_order_id 
  ON SubjectLog (order_id);
CREATE INDEX SubjectLog_name 
  ON SubjectLog (name);
CREATE UNIQUE INDEX TestOrder_order_id 
  ON TestOrder (order_id);
CREATE UNIQUE INDEX TestOrder_order_no 
  ON TestOrder (order_no);
CREATE UNIQUE INDEX RunOrder_run_id 
  ON RunOrder (run_id);
CREATE INDEX RunOrder_order_id 
  ON RunOrder (order_id);
CREATE INDEX LabLog_run_id 
  ON LabLog (run_id);
CREATE INDEX RunSegmentLog_run_segment_id 
  ON RunSegmentLog (run_segment_id);
CREATE UNIQUE INDEX TestSegment_segment_id 
  ON TestSegment (segment_id);
ALTER TABLE Specimen ADD CONSTRAINT SpecBelongsToSub FOREIGN KEY (subject_id) REFERENCES Subject (subject_id);
ALTER TABLE Specimen ADD CONSTRAINT SpecimenCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE Device ADD CONSTRAINT DevCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE DeviceSpec ADD CONSTRAINT DevSpecs FOREIGN KEY (device_id) REFERENCES Device (device_id);
ALTER TABLE TestProcedure ADD CONSTRAINT RunProcedureCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE TestDevices ADD CONSTRAINT DevicesToTest FOREIGN KEY (device_id) REFERENCES Device (device_id);
ALTER TABLE SpecimenSpec ADD CONSTRAINT SpecimenSpecs FOREIGN KEY (specimen_id) REFERENCES Specimen (specimen_id);
ALTER TABLE Device ADD CONSTRAINT DeviceAtSite FOREIGN KEY (site_id) REFERENCES Site (site_id);
ALTER TABLE TestSegmentSpec ADD CONSTRAINT TestDeviceSpec FOREIGN KEY (device_id) REFERENCES Device (device_id);
ALTER TABLE SubjectLog ADD CONSTRAINT SubjectVitalStats FOREIGN KEY (subject_id) REFERENCES Subject (subject_id);
ALTER TABLE SubjectLog ADD CONSTRAINT VitalsTakenBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE ProcSegment ADD CONSTRAINT SegmentsForProcedure FOREIGN KEY (procedure_id) REFERENCES TestProcedure (procedure_id);
ALTER TABLE RunOrder ADD CONSTRAINT ProcedureForOrder FOREIGN KEY (procedure_id) REFERENCES TestProcedure (procedure_id);
ALTER TABLE RunOrder ADD CONSTRAINT RunAtSite FOREIGN KEY (site_id) REFERENCES Site (site_id);
ALTER TABLE RunOrder ADD CONSTRAINT RunOrderForSpecimen FOREIGN KEY (specimen_id) REFERENCES Specimen (specimen_id);
ALTER TABLE RunSegment ADD CONSTRAINT RunOrderSegment FOREIGN KEY (run_id) REFERENCES RunOrder (run_id);
ALTER TABLE LabLog ADD CONSTRAINT LabForRunOrder FOREIGN KEY (run_id) REFERENCES RunOrder (run_id);
ALTER TABLE OrderDetails ADD CONSTRAINT OrderDetails FOREIGN KEY (order_id) REFERENCES TestOrder (order_id);
ALTER TABLE TestOrder ADD CONSTRAINT OrderAtSite FOREIGN KEY (site_id) REFERENCES Site (site_id);
ALTER TABLE TestOrder ADD CONSTRAINT OrderForSubject FOREIGN KEY (subject_id) REFERENCES Subject (subject_id);
ALTER TABLE SubjectLog ADD CONSTRAINT SubjectLogForOrder FOREIGN KEY (order_id) REFERENCES TestOrder (order_id);
ALTER TABLE Subject ADD CONSTRAINT SubjectCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE RunOrder ADD CONSTRAINT RunOrderCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE TestDevices ADD CONSTRAINT RunProcedureDevices FOREIGN KEY (procedure_id) REFERENCES TestProcedure (procedure_id);
ALTER TABLE RunOrder ADD CONSTRAINT SubOrder FOREIGN KEY (order_id) REFERENCES TestOrder (order_id);
ALTER TABLE TestOrder ADD CONSTRAINT OrderCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE Site ADD CONSTRAINT SiteCreatedBy FOREIGN KEY (created_by) REFERENCES Users (user_id);
ALTER TABLE ProcSegment ADD CONSTRAINT SegmentInProc FOREIGN KEY (segment_id) REFERENCES TestSegment (segment_id);
ALTER TABLE TestSegmentSpec ADD CONSTRAINT SegmentForTest FOREIGN KEY (segment_id) REFERENCES TestSegment (segment_id);
ALTER TABLE RunSegment ADD CONSTRAINT RunSegment FOREIGN KEY (segment_id) REFERENCES TestSegment (segment_id);
ALTER TABLE RunSegmentLog ADD CONSTRAINT LogForRunSegment FOREIGN KEY (run_segment_id) REFERENCES RunSegment (run_segment_id);
