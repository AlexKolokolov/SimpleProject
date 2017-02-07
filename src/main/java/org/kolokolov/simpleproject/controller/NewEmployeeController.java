package org.kolokolov.simpleproject.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;

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
    
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    private String firstName;
    private String lastName;
    
    private Department department;
    
    private List<Department> departments;
    
    private Employee employee;
    
    
    
    public NewEmployeeController() {
        System.out.println("NewEmployeeController instantiated");
        departments = departmentService.getDepartments();
    }

//    {
//        departments = departmentService.getDepartments();
//    }
    
    public String getMessage() {
        String msg;
        if (firstName == null || lastName == null) {
            msg = "";
        } else {
            employee = new Employee(firstName, lastName, department);
            employeeService.addNewEmployee(employee);
            msg = String.format("New employee %s has been added", employee);
        }
        return msg;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
