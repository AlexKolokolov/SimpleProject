create or replace PACKAGE emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER,
                    p_error_code OUT NUMBER);
END emp_manage;
/

create or replace PACKAGE BODY emp_manage AS
	PROCEDURE del_emp(p_id IN INTEGER, 
                    p_error_code OUT NUMBER) 
  IS
	v_dep_id NUMBER;
    v_emp_age NUMBER;
    v_emp_gender NUMBER;
	BEGIN
    SELECT department_id INTO v_dep_id FROM employee WHERE employee_id = p_id;
    SELECT age INTO v_age FROM employee WHERE employee_id = p_id;
    SELECT gender INTO v_gender FROM employee WHERE employee_id = p_id;
		IF (v_dep_id != constants.dep_chairmanship AND v_age >= 30 AND v_age <= 50 AND v_gender = 1) 
    THEN
			DELETE FROM employee e WHERE e.employee_id = p_id;
      p_error_code := 0;
      COMMIT;
    ELSE IF (v_emp_age < 30 OR v_age > 50) THEN
      p_error_code := 3;
    ELSE IF (v_gender = 0) THEN
      p_error_code := 2;
    ELSE
      p_error_code := 1;
		END IF;
	END del_emp;
END emp_manage;
/
				