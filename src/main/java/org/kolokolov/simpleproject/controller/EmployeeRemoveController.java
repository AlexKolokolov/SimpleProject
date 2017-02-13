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
public class EmployeeRemoveController {
    
    private Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    
    Integer errorCode;

    private String id = params.get("employeeId");
       
    private Employee employeeToRemove;
    
    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public String getMessage() {
    	
        String msg;
        if (errorCode == null) {
            msg = "";
        } else {
        	switch (errorCode) {
			case 0:
				msg = String.format("Employee %s has been removed (Error code: %d)", employeeToRemove, errorCode);
				break;
			case 1:
				msg = String.format("Employee %s cannot be removed because he is a chirman (Error code: %d)", employeeToRemove, errorCode);
				break;
			case 2:
				msg = String.format("Female employee %s cannot be removed (Error code: %d)", employeeToRemove, errorCode);
				break;
			case 3:
				msg = String.format("Employee %s cannot be removed because of age (Error code: %d)", employeeToRemove, errorCode);
				break;
			default:
				msg = "";
				break;
			}
        }
        return msg;
    }
    
    public void removeEmployee() {
    	employeeToRemove = employeeService.getEmployeeById(id);
    	errorCode = employeeService.removeEmployee(id);
    }
}
