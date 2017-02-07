package org.kolokolov.simpleproject.service;

import java.util.List;

import org.kolokolov.simpleproject.dao.DepartmentDAO;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO departmentDAO;
    
    public DepartmentService() {
        System.out.println("DepartmentService instantiated");
    }

    public List<Department> getDepartments() {
        return departmentDAO.getAllDepartments();
    }

    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }
}
