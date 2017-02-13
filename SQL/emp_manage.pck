create or replace PACKAGE emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER
                    p_error_code OUT NUMBER);
END emp_manage;
/

create or replace PACKAGE BODY emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER, 
                    p_error_code OUT NUMBER) 
  IS
		v_dep_id NUMBER;
	BEGIN
    SELECT department_id INTO v_dep_id FROM employee WHERE employee_id = p_id;
		IF (v_dep_id != constants.dep_chairmanship) THEN
			DELETE FROM employee e WHERE e.employee_id = p_id;
      p_error_code := 0;
    ELSE
      p_error_code := 1;
		END IF;
	END del_emp;
END emp_manage;
/
				