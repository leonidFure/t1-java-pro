package org.example.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.example.domain.exception.DataBaseException;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
	private final PGConnectionPoolDataSource dataSource;

	@Override
	public Long create(User user) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.prepareStatement("INSERT INTO users (username) VALUES (?)",
					 Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUsername());
			statement.executeUpdate();
			final var generatedKeys = statement.getGeneratedKeys();
			return generatedKeys.next()
					? generatedKeys.getLong(1)
					: null;
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement()) {
			statement.executeUpdate("DELETE FROM users WHERE id = " + id);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public User getById(Long id) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement();
			 final var resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id)) {
			return resultSet.next()
					? new User(resultSet.getLong("id"), resultSet.getString("username"))
					: null;
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public Collection<User> getAll() {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement();
			 final var resultSet = statement.executeQuery("SELECT * FROM users")) {
			final var result = new LinkedList<User>();
			while (resultSet.next()) {
				result.add(new User(
						resultSet.getLong("id"),
						resultSet.getString("username")
				));
			}
			return result;
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
}
