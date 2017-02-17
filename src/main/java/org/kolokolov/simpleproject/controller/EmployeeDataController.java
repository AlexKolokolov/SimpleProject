package org.kolokolov.simpleproject.controller;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.Event;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.kolokolov.simpleproject.service.EventService;
import org.kolokolov.simpleproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@ManagedBean
public class EmployeeDataController {
    
    private Employee employee;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private FileService fileService;
    
    @Autowired
    private EventService eventService;
    
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return employees;
    }
    
    public Employee getEmployee() {
    	return employee;
    }

    public List<Employee> getSubordinates() {
    	return employeeService.getSubordinates(employee);
    }
    
    public Map<String,String> getContacts() {
    	return employeeService.getEmployeeById(employee.getId()).getContacts();
    }
    
    public Map<Integer,String> getEmployeeFilesDescriptions() {
    	return fileService.getFileDescriptions(employee.getId());
    }
    
    public List<Event> getEmployeeEvents() {
    	return eventService.getEmployeeEvents(employee.getId());
    }

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
