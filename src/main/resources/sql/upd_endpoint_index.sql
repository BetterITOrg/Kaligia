set search_path=kaligia;
DROP INDEX IF EXISTS endpoint_name;
CREATE UNIQUE INDEX endpoint_name
   ON endpoint (site_id, name);
