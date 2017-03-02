package org.kolokolov.simpleproject.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.kolokolov.simpleproject.hstore.HstoreUserType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="employee")
@TypeDef(name="hstore", typeClass=HstoreUserType.class)
public class Employee {
	
	private static Logger logger = LogManager.getLogger();
    
	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="gender")
	@Enumerated(EnumType.ORDINAL)
	private Gender gender;
	
	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	@Column(name="age")
	private int age;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="chief_id")
	private Employee chief;
    
//	@JsonIgnore
	@JsonProperty("department")
	@ManyToOne
	@JoinColumn(name="department_id")
    private Department department;
	
	@Column(name = "salary", columnDefinition="DECIMAL(7,2)")
	private BigDecimal salary;
	
	@JsonIgnore
	@Type(type="hstore")
	@Column(name="contacts")
	private Map<String, String> contacts = new LinkedHashMap<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="employee", cascade=CascadeType.ALL)
    private List<EmployeeFile> employeeFiles = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy="employee", cascade=CascadeType.ALL)
    private List<Event> employeeEvents = new ArrayList<>();

    public Employee() {}
    
    public Employee(String firstName, String lastName, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
    
    public Employee(String firstName, String lastName, Gender gender, int age, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
        this.department = department;
    }
    
    public void addContact(String key, String value) {
    	contacts.put(key, value);
    }
    
    public void addFile(EmployeeFile file) {
    	logger.debug("addFile method runs");
    	employeeFiles.add(file);
    }

    public List<EmployeeFile> getEmployeeFiles() {
		return employeeFiles;
	}

	public void setEmployeeFiles(List<EmployeeFile> employeeFiles) {
		this.employeeFiles = employeeFiles;
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
    
    public Map<String, String> getContacts() {
		return contacts;
	}

	public void setContacts(Map<String, String> contacts) {
		this.contacts = contacts;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<Event> getEmployeeEvents() {
		return employeeEvents;
	}

	public void setEmployeeEvents(List<Event> employeeEvents) {
		this.employeeEvents = employeeEvents;
	}

	public Employee getChief() {
		return chief;
	}

	public void setChief(Employee chief) {
		this.chief = chief;
	}
	
	public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "[ID: " + id + ", First name: " + firstName + ", Last name: " + lastName + ", age: " + age + ", gender: " + gender + ", Department: " + department.getName() + "]";
    }
}
