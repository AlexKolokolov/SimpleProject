package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class MockDepartmentDAO implements DepartmentDAO {
    
    private static Logger logger = LogManager.getLogger();
    
    private List<Department> departments;
    
    public MockDepartmentDAO() {
       logger.debug("MockDepartmentDAO instantiated");
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
