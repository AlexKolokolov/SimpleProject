create or replace PACKAGE emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER);
END emp_manage;
/

create or replace PACKAGE BODY emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER) IS
		dep_name VARCHAR2(20);
		forbidden_dep VARCHAR2(20);
	BEGIN
		forbidden_dep := 'Chairmanship';
    SELECT d.name INTO dep_name FROM department d NATURAL JOIN employee e WHERE employee_id = p_id;
		IF (dep_name != forbidden_dep) THEN
			DELETE FROM employee e WHERE e.employee_id = p_id;
		END IF;
	END del_emp;
END emp_manage;
/
				