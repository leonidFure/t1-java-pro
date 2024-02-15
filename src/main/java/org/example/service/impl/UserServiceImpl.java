package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserDao userDao;

	@Override
	public Long create(User user) {
		return userDao.create(user);
	}

	@Override
	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public User getById(Long id) {
		return userDao.getById(id);
	}

	@Override
	public Collection<User> getAll() {
		return userDao.getAll();
	}
}
