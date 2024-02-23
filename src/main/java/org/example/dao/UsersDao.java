package org.example.dao;

import org.example.domain.users.User;

import java.util.Collection;
import java.util.Optional;

public interface UsersDao {
	Optional<Long> create(User user);

	void delete(Long id);

	Optional<User> getById(Long id);

	Collection<User> getAll();
}