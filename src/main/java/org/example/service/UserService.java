package org.example.service;

import org.example.domain.User;

import java.util.Collection;

public interface UserService {
	Long create(User user);

	void delete(Long id);

	User getById(Long id);

	Collection<User> getAll();
}
