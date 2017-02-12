CREATE OR REPLACE PACKAGE emp_manage AS
	del_emp(p_id IN INTEGER);
END emp_manage;
/

CREATE OR REPALCE PACKAGE BODY emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER) IS
	DECLARE
		dep_name VARCHAR(20);
		forbidden_dep VARCHAR(20);
	BEGIN
		SET SERVEROUTPUT ON
		forbidden_dep := 'Chairmanship';
		dep_name := SELECT d.name FROM department d NATURAL JOIN employee e WHERE employee_id = p_id;
		IF (dep_name != forbidden_dep) THEN
			DELETE FROM employee e WHERE e.employee_id = p_id;
		ELSE
			DBMS_OUTPUT.enable;
			DBMS_OUTPUT.put_line('Unable to delete employee from '||forbidden_dep||' department');
		END IF;
	END del_emp;
END emp_manage;
/ 
				