package org.example.dao.impl;

import lombok.RequiredArgsConstructor;
import org.example.dao.ProductDao;
import org.example.domain.exceptions.DataBaseException;
import org.example.domain.products.Product;
import org.example.domain.products.ProductType;
import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
	private final PGConnectionPoolDataSource dataSource;

	@Override
	public Optional<Long> create(Product user) {
		final var insertSql = "INSERT INTO products (user_id, product_number, balance, type) VALUES (?, ?, ?, ?)";
		try (final var connection = dataSource.getPooledConnection().getConnection();

			 final var statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
			statement.setLong(1, user.getUserId());
			statement.setLong(2, user.getProductNumber());
			statement.setBigDecimal(3, user.getBalance());
			statement.setString(4, user.getType().toString());
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
			statement.executeUpdate("DELETE FROM products WHERE id = " + id);
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public Optional<Product> getById(Long id) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement();
			 final var resultSet = statement.executeQuery("SELECT * FROM products WHERE id = " + id)) {
			return resultSet.next()
					? Optional.of(fromResultSet(resultSet))
					: Optional.empty();
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	@Override
	public Collection<Product> getAll(Long userId) {
		try (final var connection = dataSource.getPooledConnection().getConnection();
			 final var statement = connection.createStatement();
			 final var resultSet = statement.executeQuery("SELECT * FROM users")) {
			final var result = new LinkedList<Product>();
			while (resultSet.next()) {
				result.add(fromResultSet(resultSet));
			}
			return result;
		} catch (SQLException e) {
			throw new DataBaseException(e.getMessage());
		}
	}

	private static Product fromResultSet(ResultSet resultSet) throws SQLException {
		return Product.builder()
				.id(resultSet.getLong("id"))
				.userId(resultSet.getLong("user_id"))
				.productNumber(resultSet.getLong("product_number"))
				.balance(resultSet.getBigDecimal("balance"))
				.type(ProductType.valueOf(resultSet.getString("type")))
				.build();
	}
}