-- Database: db_myglic

-- DROP DATABASE IF EXISTS db_myglic;

CREATE DATABASE db_myglic
    WITH
    OWNER = usr_myglic
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;



-- Database: db_myglic_test

-- DROP DATABASE IF EXISTS db_myglic_test;

CREATE DATABASE db_myglic_test
    WITH
    OWNER = usr_myglic
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;