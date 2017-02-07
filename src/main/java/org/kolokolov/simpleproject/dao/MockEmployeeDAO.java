package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class MockEmployeeDAO implements EmployeeDAO {
    
    int lastId;
    
    private Map<String, Employee> employees;
  
    
    {
        Department dep = new Department(1, "Management");
        employees = new LinkedHashMap<String, Employee>();
        addNewEmployee(new Employee("John", "Smith", dep));
        addNewEmployee(new Employee("David", "Malan", dep));
        addNewEmployee(new Employee("Ron", "Perlman", dep));
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
