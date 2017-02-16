package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.kolokolov.simpleproject.model.Action;
import org.kolokolov.simpleproject.model.Event;

public interface HistoryDAO {
	void addEvent(Event event);
	List<Event> getEmployeeEvents(int employeeId);
	Action getAction(int actionId);
}
