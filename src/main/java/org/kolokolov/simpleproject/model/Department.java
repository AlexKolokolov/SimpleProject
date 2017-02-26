package org.kolokolov.simpleproject.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Column(name="name")
    private String name;
    
	@JsonIgnore
	@OneToMany(mappedBy="department")
    private List<Employee> employees;
    
    {
    	employees = new ArrayList<>();
    }

    public Department() {}

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public void addEmployee(Employee employee) {
    	employees.add(employee);
    }
    
    public void removeEmployee(Employee employee) {
    	Iterator<Employee> iterator = employees.iterator();
    	while (iterator.hasNext()) {
    		if (employee.equals(iterator.next())) {
    			iterator.remove();
    		}
    	}
    }

	@Override
	public String toString() {
		return "[" + id + ", " + name + "]";
	}
}
