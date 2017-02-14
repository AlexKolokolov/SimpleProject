create or replace PACKAGE dep_manage AS
  TYPE dep_t IS RECORD
  (
    id NUMBER,
    name VARCHAR2(20)
  );
  TYPE dep_table_t IS TABLE OF dep_t;
  FUNCTION f_get_empty_deps RETURN dep_table_t;
END dep_manage;
/

create or replace PACKAGE BODY dep_manage AS
  --Function returns empty departments
  FUNCTION f_get_empty_deps RETURN dep_table_t
  IS
    v_empty_deps dep_table_t;
  BEGIN
    SELECT d.department_id, d.name BULK COLLECT INTO v_empty_deps FROM department d LEFT JOIN employee e ON e.department_id = d.department_id WHERE e.department_id IS NULL;
    RETURN v_empty_deps;
	END f_get_empty_deps;

END dep_manage;
/