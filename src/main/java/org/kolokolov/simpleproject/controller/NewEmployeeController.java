package org.kolokolov.simpleproject.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private String gender = "1";
    
    private String departmentId;
    
    private Map<String, String> departments;
    List<Integer> ages;
    
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
            msg = String.format("New employee %s has been added", employee);
        }
        return msg;
    }
    
    public void addNewEmployee() {
    	Department department = departmentService.getDepartmentById(departmentId);
    	logger.debug("department: " + department);
    	employee = new Employee(firstName, lastName, Integer.parseInt(gender), Integer.parseInt(age), department);
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
}
