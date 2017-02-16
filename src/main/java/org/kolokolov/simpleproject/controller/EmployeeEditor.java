package org.kolokolov.simpleproject.controller;

import java.util.Map;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeEditor {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private EmployeeService employeeService;

	private Employee employee;
	
	private String firstName;
	private String lastName;
	private Department department;
	
	private String departmentId;
	
	private Map<String, String> departments;
	
	public EmployeeEditor() {
		logger.debug("Employee for editing: " + employee);
	}
	
	public void editEmployee(ActionEvent event) {
		departments = departmentService.getDepartments().stream().collect(Collectors.toMap(d -> d.getName(), d -> String.valueOf(d.getId())));
		employee = (Employee) event.getComponent().getAttributes().get("employee");
		logger.debug("Employee for editing: " + employee);
		if (employee != null) {
			firstName = employee.getFirstName();
			lastName = employee.getLastName();
			department = employee.getDepartment();
		}
	}
	
	public String saveEditedEmployee() {
		logger.debug("Employee for editing: " + employee);
		if (department.getId() != Integer.parseInt(departmentId)) {
			department = departmentService.getDepartmentById(departmentId);
		}
		employee.setDepartment(department);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employeeService.saveEmployee(employee);
		return "employee";
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Map<String, String> getDepartments() {
		return departments;
	}

	public void setDepartments(Map<String, String> departments) {
		this.departments = departments;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
