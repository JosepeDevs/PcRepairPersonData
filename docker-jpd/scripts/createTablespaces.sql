-- Create an tablespace
-- =============================================================
-- Create new tablespace for person metadata
-- =============================================================
CREATE TABLESPACE JPD_PERSON_METADATA
  DATAFILE 'jpd_person_metadata.dbf'
  SIZE 100M
  AUTOEXTEND ON NEXT 10M MAXSIZE 500M
  EXTENT MANAGEMENT LOCAL
  SEGMENT SPACE MANAGEMENT AUTO;

-- =============================================================
-- Grant permissions to application user
-- =============================================================
alter user test_user quota unlimited on JPD_PERSON_METADATA;

GRANT UNLIMITED TABLESPACE TO test_user;

