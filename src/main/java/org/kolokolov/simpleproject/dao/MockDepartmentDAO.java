package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.kolokolov.simpleproject.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class MockDepartmentDAO implements DepartmentDAO {
    
    private List<Department> departments;
    
    public MockDepartmentDAO() {
        System.out.println("MockDepartmentDAO instantiated");
    }

    {
        departments = new ArrayList<Department>();
        departments.add(new Department(1, "Management"));
        departments.add(new Department(2, "Production"));
        departments.add(new Department(3, "Security"));
    }

    public List<Department> getAllDepartments() {
        return departments;
    }

}
