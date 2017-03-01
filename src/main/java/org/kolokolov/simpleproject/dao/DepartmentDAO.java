package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.DepartmentStatistic;

public interface DepartmentDAO {
    
    List<Department> getAllDepartments();
    
    List<Department> getEmptyDepartments();
    
    Department getDepartmentById(int id);

    List<DepartmentStatistic> getDepartmentStatistic();
}
