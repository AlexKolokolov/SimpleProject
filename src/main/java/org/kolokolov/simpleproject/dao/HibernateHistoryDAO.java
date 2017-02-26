package org.kolokolov.simpleproject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.Action;
import org.kolokolov.simpleproject.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateHistoryDAO implements HistoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addEvent(Event event) {
		Session session = sessionFactory.getCurrentSession();
		session.save(event);
	}

	@Override
	@Transactional
	public List<Event> getEmployeeEvents(int employeeId) {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("FROM Event e WHERE e.employee.id = :id", Event.class).setParameter("id", employeeId).getResultList();
	}
	
	@Override
	@Transactional
	public Action getAction(int actionId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Action.class, actionId);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
