package org.example.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.UsersDao;
import org.example.domain.users.User;
import org.example.domain.exceptions.DataBaseException;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsersDaoImpl implements UsersDao {
	private final PGConnectionPoolDataSource dataSource;

	@Override
	public Optional<Long> create(User user) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.prepareStatement("INSERT INTO users (username) VALUES (?)",
					 Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, user.getUsername());
			statement.executeUpdate();
			final var generatedKeys = statement.getGeneratedKeys();
			return generatedKeys.next()
					? Optional.of(generatedKeys.getLong(1))
					: Optional.empty();
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
	public Optional<User> getById(Long id) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement();
			 final var resultSet = statement.executeQuery("SELECT * FROM users WHERE id = " + id)) {
			return resultSet.next()
					? Optional.of(User.of(
					resultSet.getLong("id"),
					resultSet.getString("username")))
					: Optional.empty();
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
				result.add(User.of(
						resultSet.getLong("id"),
						resultSet.getString("username")));
			}
			return result;
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
}