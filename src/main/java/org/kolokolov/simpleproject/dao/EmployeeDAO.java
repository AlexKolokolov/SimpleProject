package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Employee;
import org.kolokolov.simpleproject.model.EmployeeFile;
import org.kolokolov.simpleproject.model.EmployeeStatistic;

public interface EmployeeDAO {
	
    List<Employee> getAllEmployees();
    
    List<Employee> getEmployeesByLastName(String lastName);
    
    int addNewEmployee(Employee employee);
    
    void removeEmployee(int employeeId);
    
    Employee getEmployeesById(int employeeId);
	
	void addNewContact(int employeeId, String contactType, String contactValue);
	
	List<EmployeeFile> getFiles(int employeeId);
	
	void persistEmployee(Employee employee);
	
	Employee getDepartmentChief(int departmnetId);
	
	List<Employee> getSubordinates(Employee employee);

	List<Employee> getEmployeesOfDepartment(int departmentId);

	List<Employee> getChiefs(Employee employee);

    List<EmployeeStatistic> getEmployeeStatistics();
}
