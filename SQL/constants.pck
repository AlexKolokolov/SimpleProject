create or replace PACKAGE constants AS
	--constants for departments
  dep_chairmanship NUMBER := 1;
  dep_production NUMBER := 2;
  dep_management NUMBER := 3;
  dep_security NUMBER := 4;
  
  --constants for employee
  emp_min_age_to_remove NUMBER := 30;
  emp_max_age_to_remove NUMBER := 49;
  
  --error codes
  no_err_code NUMBER := 0;
  department_err_code NUMBER := 1;
  gender_err_code NUMBER := 2;
  age_err_code NUMBER := 3;
  
END;