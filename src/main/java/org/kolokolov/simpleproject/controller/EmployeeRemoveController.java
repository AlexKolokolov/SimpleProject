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
    
    Integer errorCode;
    
    private Employee employeeToRemove;
    
    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    public String getMessage() {
    	
        String msg;
        if (errorCode == null) {
            msg = "";
        } else {
        	switch (errorCode) {
			case 0:
				msg = String.format("Employee %s %s has been removed", employeeToRemove.getFirstName(), employeeToRemove.getLastName());
				break;
			case 1:
				msg = String.format("Employee %s %s cannot be removed because he is a chairman", employeeToRemove.getFirstName(), employeeToRemove.getLastName());
				break;
			case 2:
				msg = String.format("Female employee %s %s cannot be removed", employeeToRemove.getFirstName(), employeeToRemove.getLastName());
				break;
			case 3:
				msg = String.format("Employee %s %s cannot be removed because of age", employeeToRemove.getFirstName(), employeeToRemove.getLastName());
				break;
			default:
				msg = "";
				break;
			}
        }
        return msg;
    }
    
    public void removeEmployee() {
    	errorCode = employeeService.removeEmployee(String.valueOf(employeeToRemove.getId()));
    }

	public Employee getEmployeeToRemove() {
		return employeeToRemove;
	}

	public void setEmployeeToRemove(Employee employeeToRemove) {
		this.employeeToRemove = employeeToRemove;
	}
}
