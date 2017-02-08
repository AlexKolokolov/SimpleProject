package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Department;

public interface DepartmentDAO {
    
    List<Department> getAllDepartments();
    
    List<Department> getEmptyDepartments();
    
    Department getDepartmentById(String id);
}
