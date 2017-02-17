create or replace PACKAGE dep_manage AS
  FUNCTION f_get_empty_deps RETURN dep_table_type;
END dep_manage;
/

create or replace PACKAGE BODY dep_manage AS
  --Function returns empty departments
FUNCTION f_get_empty_deps RETURN dep_table_type
IS
  v_empty_deps dep_table_type;
BEGIN
  SELECT dep_type(d.department_id, d.name) BULK COLLECT INTO v_empty_deps FROM department d LEFT JOIN employee e ON e.department_id = d.department_id WHERE e.department_id IS NULL;
  RETURN v_empty_deps;
END f_get_empty_deps;

END dep_manage;
/