package org.kolokolov.simpleproject.service;

import java.util.List;

import org.kolokolov.simpleproject.dao.HistoryDAO;
import org.kolokolov.simpleproject.model.Action;
import org.kolokolov.simpleproject.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

	@Autowired
	private HistoryDAO historyDAO;
	
	public void addEvent(Event event) {
		historyDAO.addEvent(event);
	}

	public void setHistoryDAO(HistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}
	
	public Action getAction(int actionId) {
		return historyDAO.getAction(actionId);
	}
	
	public List<Event> getEmployeeEvents(int employeeId) {
		return historyDAO.getEmployeeEvents(employeeId);
	}
}
