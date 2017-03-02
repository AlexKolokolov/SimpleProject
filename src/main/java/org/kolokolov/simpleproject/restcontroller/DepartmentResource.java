package org.kolokolov.simpleproject.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.DepartmentStatistic;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value="/departments")
public class DepartmentResource {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Department>> getDepartments() {
		logger.debug("Department service: " + departmentService);
		List<Department> departments = departmentService.getDepartments();
		return departments != null ? 
		        new ResponseEntity<>(departments, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/{departmentId}", method=RequestMethod.GET)
	public ResponseEntity<Department> getDepartment(@PathVariable int departmentId) {
	    Department department = departmentService.getDepartmentById(departmentId);
	    ResponseEntity<Department> responseEntity = department != null ?
	            new ResponseEntity<>(department, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return responseEntity;
	}
	
	@RequestMapping(value="/statistics", method=RequestMethod.GET)
    public ResponseEntity<List<DepartmentStatistic>> getDepartmentsStatistics() {
        logger.debug("Department service: " + departmentService);
        List<DepartmentStatistic> stats = departmentService.getStatistic();
        return stats != null ? 
                new ResponseEntity<>(stats, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
