package org.kolokolov.simpleproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usr_seq")
    @SequenceGenerator(name="usr_seq", sequenceName="USR_SEQ", allocationSize=1)
	private int id;
	
	@Column(name="user_name", unique=true)
	private String name;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_state")
	@Enumerated(EnumType.ORDINAL)
	private State state = State.ACTIVE;
	
	@Column(name="user_profile")
	@Enumerated(EnumType.ORDINAL)
	private UserProfile profile;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public UserProfile getUserProfile() {
		return profile;
	}

	public void setUserProfile(UserProfile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", state=" + state.getState() + ", profile="
				+ profile + "]";
	}
}
