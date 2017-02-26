package org.kolokolov.simpleproject.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.kolokolov.simpleproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateUserDAO implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public User getUserByName(String userName) {
		Session session = sessionFactory.getCurrentSession();
		return (User) session.createQuery("FROM User WHERE name = :name").setParameter("name", userName).getSingleResult();
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
