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
public class EmployeeRemoveController {
    
    private String id;
    
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
        if (id == null) {
            msg = "";
        } else {
            Employee employeeToRemove = employeeService.getAllEmployeeById(id);
            if (employeeToRemove != null) {
                msg = String.format("Employee %s has been removed", employeeToRemove);
                employeeService.removeEmployee(id);
            } else {
                msg = String.format("There is no employee with id %s", id);
            }
        }
        return msg;
    }
}
