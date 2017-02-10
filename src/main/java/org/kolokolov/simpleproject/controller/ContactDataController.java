package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
//@RequestScope
public class ContactDataController {
	
	private static Logger logger = LogManager.getLogger();

	@Autowired
	private EmployeeService employeeService;

	private String employeeId;
	private Employee employee;
	private String contactType;
	private String contactValue;

	public void addNewContact() {
		logger.debug("addNewContact method runs");
		employee.addContact(contactType, contactValue);
		logger.debug("add new contact: " + contactType + " -> " + contactValue);
		employeeService.persistEmployee(employee);
		logger.debug("persist employee: " + employee);
	}

	public String getMessage() {
		String msg;
		if (contactType == null || contactValue == null) {
			msg = "";
		} else {
			msg = String.format("New contact %s : %s has been added", contactType, contactValue);
		}
		return msg;
	}

	public Employee getEmployee() {
		return employeeService.getAllEmployeeById(employeeId);
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
