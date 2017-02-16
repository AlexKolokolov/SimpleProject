package org.kolokolov.simpleproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="history")
public class Event {
	
	@Id
	@Column(name="event_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="evn_seq")
    @SequenceGenerator(name="evn_seq", sequenceName="EVN_SEQ", allocationSize=1)
	private int id;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="action_id")
	private Action action;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_time")
	private Date date;

	public Event() {}
	
	public Event(String description, Action action, Employee employee, Date date) {
		this.description = description;
		this.action = action;
		this.employee = employee;
		this.date = date;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "[ID: " + id + " : " + description + " : " + action.getName() + " : " + date + "]";
	}
}