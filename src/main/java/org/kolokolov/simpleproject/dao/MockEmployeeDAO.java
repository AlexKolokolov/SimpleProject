package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kolokolov.simpleproject.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class MockEmployeeDAO implements EmployeeDAO {
    
    int lastId;
    
    private Map<String, Employee> employees;
    
    {
        employees = new LinkedHashMap<String, Employee>();
        addNewEmployee(new Employee("John", "Smith"));
        addNewEmployee(new Employee("David", "Malan"));
        addNewEmployee(new Employee("Ron", "Perlman"));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> getEmployeesByLastName() {
        // TODO Auto-generated method stub
        return null;
    }

    public void addNewEmployee(Employee employee) {
        employee.setId(++lastId);
        employees.put(String.valueOf(employee.getId()), employee);
    }
    
    public void removeEmployee(String id) {
        employees.remove(id);
    }
    
    public Employee getEmployeesById(String id) {
        return employees.get(id);
    }
}
