package org.kolokolov.simpleproject.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<List<Employee>> getEmployees() {
		logger.debug("Emloyee service: " + employeeService);
		List<Employee> allEmployees = employeeService.getAllEmployees();
		return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee newEmployee) {
	    logger.debug("New employee is being added");
        employeeService.addNewEmployee(newEmployee);
//        logger.debug("new employee id: " + newEmployee);
        return new ResponseEntity<Employee>(newEmployee, HttpStatus.OK);
    }
	
	@RequestMapping(value="/{employeeId}", method=RequestMethod.GET)
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
	    Employee employee = employeeService.getEmployeeById(employeeId);
	    ResponseEntity<Employee> responseEntity = (employee != null) ? 
	            new ResponseEntity<>(employee, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
}
