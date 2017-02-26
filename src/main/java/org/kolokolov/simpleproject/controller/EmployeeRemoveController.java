package org.kolokolov.simpleproject.controller;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class EmployeeRemoveController {
    
    private Employee employeeToRemove;
    
    @Autowired
    private EmployeeService employeeService;

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    public String getMessage() {
        String msg;
        if (employeeToRemove == null) {
            msg = "";
        } else {
			msg = String.format("Employee %s %s has been fired", employeeToRemove.getFirstName(), employeeToRemove.getLastName());
        }
        return msg;
    }
    
    @Secured({"ROLE_ADMIN"})
    public void removeEmployee() {
    	employeeService.removeEmployee(employeeToRemove.getId());
    }

	public Employee getEmployeeToRemove() {
		return employeeToRemove;
	}

	public void setEmployeeToRemove(Employee employeeToRemove) {
		this.employeeToRemove = employeeToRemove;
	}
}
