set search_path=kaligia;

CREATE TABLE FLRemovedLog (
  run_segment_id int4 NOT NULL, 
  r_index        int4, 
  wavelength     float8, 
  photon_count   float8);

CREATE INDEX FLRemovedLog_run_segment_id 
  ON FLRemovedLog (run_segment_id);

ALTER TABLE FLRemovedLog ADD CONSTRAINT FLRemovedSegment FOREIGN KEY (run_segment_id) REFERENCES RunSegment (run_segment_id);
