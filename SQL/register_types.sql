create or replace TYPE dep_type AS OBJECT
(
id NUMBER,
name VARCHAR2(20)
);
/

create or replace TYPE dep_table_type IS TABLE OF dep_type;
/