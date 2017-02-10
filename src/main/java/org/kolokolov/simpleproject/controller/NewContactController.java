package org.kolokolov.simpleproject.controller;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class NewContactController {

	@Autowired
	private EmployeeService employeeService;
	
	private Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

	private String employeeId = params.get("employeeId");	
	
	private String contactType;
	private String contactValue;
	
	public void addNewContact() {
		employeeService.addNewContactToEmploye(employeeId, contactType, contactValue);
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
