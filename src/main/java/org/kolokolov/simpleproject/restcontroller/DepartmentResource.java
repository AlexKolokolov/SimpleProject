package org.kolokolov.simpleproject.restcontroller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Department> getDepartments() {
		logger.debug("Department service: " + departmentService);
		return departmentService.getDepartments();
	}
	
	@RequestMapping(value="/{departmentId}", method=RequestMethod.GET)
	public Department getDepartment(@PathVariable int departmentId) {
		return departmentService.getDepartmentById(departmentId);
	}

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
}
