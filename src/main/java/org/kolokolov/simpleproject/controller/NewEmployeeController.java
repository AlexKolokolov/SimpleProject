package org.kolokolov.simpleproject.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.Gender;
import org.kolokolov.simpleproject.model.Status;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class NewEmployeeController {
    
    private static Logger logger = LogManager.getLogger();
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    private String firstName;
    private String lastName;
    
    private String age;
    private String gender = "MALE";
    
    private String departmentId = "1";
    
    private String chiefId;
    
    private Map<String,String> departments;
    private List<Integer> ages;
    
    private Employee employee;
    
    {
    	ages = Stream.iterate(18, n -> n + 1).limit(72).collect(Collectors.toList());
    	logger.debug("Ages: " + ages);
    }
    
    public NewEmployeeController() {
        logger.debug("NewEmployeeController instantiated");
    }

    public void init() {
    	departments = departmentService.getDepartments().stream().collect(Collectors.toMap(d -> d.getName(), d -> String.valueOf(d.getId())));
    }
    
    public String getMessage() {
        String msg;
        if (employee == null) {
            msg = "";
        } else {
            msg = String.format("New employee %s %s has been added", employee.getFirstName(), employee.getLastName());
        }
        return msg;
    }
    
    @Secured({"ROLE_ADMIN"})
    public void addNewEmployee() {
    	logger.debug("AddNewEmployee method runs");
    	Department department = departmentService.getDepartmentById(departmentId);
    	logger.debug("department: " + department);
    	employee = new Employee(firstName, lastName, Enum.valueOf(Gender.class, gender), Integer.parseInt(age), department);
    	logger.debug("------ Chief ID: " + chiefId);
    	if (!chiefId.equals("0")) {
    		employee.setChief(employeeService.getEmployeeById(Integer.parseInt(chiefId)));
    	}
    	employee.setStatus(Status.ACTIVE);
        employeeService.addNewEmployee(employee);
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public EmployeeService getEmployeeService() {
        return employeeService;
    }
    
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

	public Map<String, String> getDepartments() {
		return departments;
	}

	public void setDepartments(Map<String, String> departments) {
		this.departments = departments;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Integer> getAges() {
		return ages;
	}
	
	public Map<String,String> getCollegues() {
		List<Employee> collegueList = null;
		if (departmentId != null) {
			collegueList = employeeService.getEmployeesOfDepartment(Integer.parseInt(departmentId));
		}
		Map<String,String> collegues = new LinkedHashMap<>();
		collegues.put("No chief", "0");
		if (collegueList != null) {
			collegueList.forEach(c -> collegues.put(c.getFirstName() + " " +  c.getLastName(), String.valueOf(c.getId())));
		}
		logger.debug("Collegues: " + collegues);
		return collegues;
	}

	public String getChiefId() {
		return chiefId;
	}

	public void setChiefId(String chiefId) {
		this.chiefId = chiefId;
	}
}
