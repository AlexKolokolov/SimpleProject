package org.kolokolov.simpleproject.dao;

import org.kolokolov.simpleproject.model.User;

public interface UserDAO {
	User getUserByName(String userName);
}
