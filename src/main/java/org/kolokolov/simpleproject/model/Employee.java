package org.kolokolov.simpleproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
    
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
    
	@ManyToOne
	@JoinColumn(name="department_id")
    private Department department;
    
    public Employee() {}
    
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = new Department(0, "MockDep");
    }
    
    public Employee(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", First name: " + firstName + ", Last name: " + lastName + ", Department: " + department.getName() + "]";
    }
}
