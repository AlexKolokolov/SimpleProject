package org.kolokolov.simpleproject.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Column(name="action")
	private String action;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="event_time")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
