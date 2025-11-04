-- =============================================================
-- Control file: persons.ctl
-- Description: SQL*Loader control file to load data into
--              the persons table
-- =============================================================
OPTIONS (ERRORS=50)
LOAD DATA
INFILE '/scripts/database-rows/PERSONS_DATA.ldr' "str '{EOL}'"
APPEND
CONTINUEIF NEXT(1:1) = '#'
INTO TABLE "PERSONS"
FIELDS TERMINATED BY ';'
OPTIONALLY ENCLOSED BY '|' AND '|'
TRAILING NULLCOLS (
    "ID_USER" CHAR(255),
    "METADATA_FILE" FILLER CHAR(255),
    "METADATA" LOBFILE("METADATA_FILE") TERMINATED BY EOF NULLIF "METADATA_FILE" = 'null',
    "NAME" CHAR(255),
    "NID_PASSPORT" CHAR(255),
    "DELETED" "TO_NUMBER(:deleted)"
)