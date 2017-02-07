package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.kolokolov.simpleproject.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class MockEmployeeDAO implements EmployeeDAO {
    
    private List<Employee> employees;
    
    {
        employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "John", "Smith"));
        employees.add(new Employee(2, "David", "Malan"));
        employees.add(new Employee(3, "Ron", "Perlman"));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<Employee> getEmployeesByLastName() {
        // TODO Auto-generated method stub
        return null;
    }

    public void addNewEmployee(Employee employee) {
        employees.add(employee);
    }
    
    public int getNextId() {
        return employees.get(employees.size() - 1).getId() + 1;
    }
}
