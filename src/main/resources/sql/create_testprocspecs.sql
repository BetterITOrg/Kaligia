set search_path=kaligia;

CREATE TABLE TestProcedureSpecs (
  procedure_id int4 NOT NULL, 
  name         varchar(64) NOT NULL, 
  value        varchar(64), 
  unit         varchar(16));

CREATE INDEX TestProcedureSpecs_name 
  ON TestProcedureSpecs (name);

ALTER TABLE TestProcedureSpecs ADD CONSTRAINT TestProcedureSpecs 
	FOREIGN KEY (procedure_id) REFERENCES TestProcedure (procedure_id);