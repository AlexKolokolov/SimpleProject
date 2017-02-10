package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class ContactDataController {
	
	@Autowired
	private EmployeeService employeeService;
	
	private Employee employee;
	private String contactType;
	private String contactValue;
	
	public void addNewContact() {
		employee.addContact(contactType, contactValue);
		employeeService.persistEmployee(employee);
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
		return employee;
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
}
