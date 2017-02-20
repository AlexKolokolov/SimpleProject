package org.kolokolov.simpleproject.model;

public enum State {
	ACTIVE("Active"),
	INACTIVE("Inactive"),
	DELETED("Deleted"),
	LOCKED("Locked");
	
	private String state;
	
	private State(String state) {
		this.state = state;
	}
	
	public String getState() {
		return state;
	}
}
