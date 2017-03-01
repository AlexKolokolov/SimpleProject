package org.kolokolov.simpleproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.DepartmentStatistic;
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
		logger.debug("getEmptyDepartments method runs");
		List<Department> emptyDepartments = departmentService.getEmptyDepartments();
		logger.debug("getEmptyDepartments method returns: " + emptyDepartments);
		if (emptyDepartments == null) {
			emptyDepartments = new ArrayList<>();
		}
		return emptyDepartments;
	}
	
	public List<DepartmentStatistic> getStatistic() {
	    return departmentService.getStatistic();
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
}
