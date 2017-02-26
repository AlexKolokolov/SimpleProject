package org.kolokolov.simpleproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_file")
public class EmployeeFile {
	
	@Id
	@Column(name="employee_file_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	@Column(name = "file_name")
	private String name;
	
	@Lob
	@Column(name="file_data")
	private byte[] data;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

	public EmployeeFile() {}

	public EmployeeFile(String name, byte[] data, Employee employee) {
		this.name = name;
		this.data = data;
		this.employee = employee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
