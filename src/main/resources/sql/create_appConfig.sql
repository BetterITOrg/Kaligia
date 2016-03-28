set search_path=kaligia;

CREATE TABLE AppConfig (
  config_id    SERIAL NOT NULL, 
  name        varchar(64) NOT NULL, 
  value       varchar(64), 
  status      varchar(16), 
  last_mod_on timestamp, 
  last_mod_by int4 NOT NULL, 
  PRIMARY KEY (config_id));
  
CREATE UNIQUE INDEX AppConfig_config_id 
  ON AppConfig (config_id);
CREATE  INDEX AppConfig_name 
  ON AppConfig (name, status);
  
ALTER TABLE AppConfig ADD CONSTRAINT ConfigCreatedBy FOREIGN KEY (last_mod_by) REFERENCES Users (user_id);