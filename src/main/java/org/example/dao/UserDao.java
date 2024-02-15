package org.example.dao;

import org.example.domain.User;

import java.util.Collection;

public interface UserDao {
	Long create(User user);

	void delete(Long id);

	User getById(Long id);

	Collection<User> getAll();
}
