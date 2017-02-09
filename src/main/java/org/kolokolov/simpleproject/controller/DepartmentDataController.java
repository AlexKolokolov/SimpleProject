package org.kolokolov.simpleproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

@Controller
@ManagedBean
@RequestScope
public class DepartmentDataController {
	
	private static Logger logger = LogManager.getLogger();
	
	@Autowired
	private DepartmentService departmentService;
	
	public DepartmentDataController() {
		logger.debug("DepartmentDataController instantiated");
	}

	public List<Department> getEmptyDepartments() {
		List<Department> emptyDepartments = departmentService.getEmptyDepartments();
		if (emptyDepartments == null) {
			emptyDepartments = new ArrayList<>();
		}
		return emptyDepartments;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
