package org.kolokolov.simpleproject.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/employees")
public class EmployeeResource {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Employee> getEmployees() {
		logger.debug("Emloyee service: " + employeeService);
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping(value="/{employeeId}", method=RequestMethod.GET)
	public Employee getEmployee(@PathVariable int employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
