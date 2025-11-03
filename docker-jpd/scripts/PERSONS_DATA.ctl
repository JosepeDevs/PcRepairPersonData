-- =============================================================
-- Control file: persons.ctl
-- Description: SQL*Loader control file to load data into
--              the persons table
-- =============================================================

LOAD DATA
INFILE '/scripts/PERSONS_DATA.ldr'
INTO TABLE persons
APPEND
FIELDS TERMINATED BY '|'
OPTIONALLY ENCLOSED BY '~'
TRAILING NULLCOLS
(
  id_user        CHAR(36),
  metadata       CHAR(4000),
  name           CHAR(200),
  nid_passport   CHAR(50),
  deleted        "TO_NUMBER(:deleted)"
)
