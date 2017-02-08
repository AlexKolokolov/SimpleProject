package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kolokolov.simpleproject.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public class MockDepartmentDAO implements DepartmentDAO {
    
    private static Logger logger = LogManager.getLogger();
    
    private Map<String, Department> departments;
    
    public MockDepartmentDAO() {
       logger.debug("MockDepartmentDAO instantiated");
    }

    {
        departments = new LinkedHashMap<String, Department>();
        addDepartment(new Department(1, "Management"));
        addDepartment(new Department(2, "Production"));
        addDepartment(new Department(3, "Security"));
    }
    
    @Override
    public List<Department> getAllDepartments() {
    	logger.debug("getAllDepartments method runs");
        return new ArrayList<>(departments.values());
    }
    
    private void addDepartment(Department department) {
    	departments.put(String.valueOf(department.getId()), department);
    }

    @Override
    public Department getDepartmentById(String id) {
    	return departments.get(id);
    }
    
    @Override
    public List<Department> getEmptyDepartments() {
    	return departments.values().stream().filter(d -> d.getEmployees().isEmpty()).collect(Collectors.toList());
    }
}
