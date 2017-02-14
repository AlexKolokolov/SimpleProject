package org.kolokolov.simpleproject.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kolokolov.simpleproject.model.Department;
import org.kolokolov.simpleproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class MockEmployeeDAO implements EmployeeDAO {
	
	@Autowired
	@Qualifier("mockDepartmentDAO")
    private DepartmentDAO departmentDAO;
    
    int lastId;
    
    private Map<String, Employee> employees;
  
    public void init() {
//        Department dep = departmentDAO.getDepartmentById("1");
        employees = new LinkedHashMap<String, Employee>();
//        addNewEmployee(new Employee("John", "Smith", dep));
//        addNewEmployee(new Employee("David", "Malan", dep));
//        addNewEmployee(new Employee("Ron", "Perlman", dep));
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<Employee>(employees.values());
    }

    public List<Employee> getEmployeesByLastName(String LastName) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addNewEmployee(Employee employee) {
        employee.setId(++lastId);
        employee.getDepartment().addEmployee(employee);
        employees.put(String.valueOf(employee.getId()), employee);
    }
    
    public Integer removeEmployee(String id) {
    	Employee employee = employees.get(id);
    	employee.getDepartment().removeEmployee(employee);
        employees.remove(id);
        return 0;
    }
    
    public Employee getEmployeesById(String id) {
        return employees.get(id);
    }
    
    @Override
    public void addNewContact(String employeeId, String contactType, String contactValue) {
    	// TODO Auto-generated method stub
    	
    }
    
    @Override
    public void addFileToEmployee(String id, byte[] bytes) {
    	// TODO Auto-generated method stub	
    }
    
    @Override
    public byte[] getFile(String id) {
    	// TODO Auto-generated method stub
    	return null;
    }
}
