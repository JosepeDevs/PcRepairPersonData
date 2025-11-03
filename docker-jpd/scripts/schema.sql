CREATE TABLE persons (
    id_user        CHAR(255)    NOT NULL,
    metadata       CLOB,
    name           VARCHAR2(255 CHAR),
    nid_passport   VARCHAR2(100 CHAR),
    deleted        NUMBER(1) DEFAULT 0,
    CONSTRAINT pk_persons PRIMARY KEY (id_user)
) LOB (metadata) STORE AS (TABLESPACE JPD_PERSON_METADATA);

CREATE INDEX idx_persons_deleted ON persons (deleted);