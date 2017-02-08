package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MockEmployeeDAO implements EmployeeDAO {
	
	@Autowired
    private DepartmentDAO departmentDAO;
    
    int lastId;
    
    private Map<String, Employee> employees;
  
    public void init() {
        Department dep = departmentDAO.getDepartmentById("1");
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
        employee.getDepartment().addEmployee(employee);
        employees.put(String.valueOf(employee.getId()), employee);
    }
    
    public void removeEmployee(String id) {
    	Employee employee = employees.get(id);
    	employee.getDepartment().removeEmployee(employee);
        employees.remove(id);
    }
    
    public Employee getEmployeesById(String id) {
        return employees.get(id);
    }

}
