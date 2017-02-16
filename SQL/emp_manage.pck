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
    SELECT department_id, age, gender INTO v_dep_id, v_emp_age, v_emp_gender FROM employee WHERE employee_id = p_id;
	IF (v_dep_id = constants.dep_chairmanship) 
    THEN
      p_error_code := constants.department_err_code;
    ELSIF (v_emp_age < constants.emp_min_age_to_remove OR v_emp_age > constants.emp_max_age_to_remove) 
    THEN
      p_error_code := constants.age_err_code;
    ELSIF (v_emp_gender = 1) 
    THEN
      p_error_code := constants.gender_err_code;
    ELSE
      UPDATE employee SET status = 1 WHERE employee_id = p_id;
      p_error_code := constants.no_err_code;
		END IF;
	END del_emp;
END emp_manage;
/
				