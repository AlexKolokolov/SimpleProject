package org.kolokolov.simpleproject.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.dao.DepartmentDAO;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    
    private static Logger logger = LogManager.getLogger();

    @Autowired
    @Qualifier("oracleHibernateDepartmentDAO")
    private DepartmentDAO departmentDAO;
    
    public DepartmentService() {
        logger.debug("DepartmentService instantiated");
    }

    public List<Department> getDepartments() {
        return departmentDAO.getAllDepartments();
    }

    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

	public Department getDepartmentById(String departmentId) {
		return departmentDAO.getDepartmentById(departmentId);
	}
	
	public List<Department> getEmptyDepartments() {
		return departmentDAO.getEmptyDepartments();
	}
}
