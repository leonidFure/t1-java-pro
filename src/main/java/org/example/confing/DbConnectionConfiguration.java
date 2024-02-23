package org.example.confing;

import org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConnectionConfiguration {

	@Bean
	public PGConnectionPoolDataSource connection(@Value("${datasource.servername}") String serverName,
												 @Value("${datasource.port}") Integer port,
												 @Value("${datasource.database.name}") String databaseName,
												 @Value("${datasource.database.username}") String username,
												 @Value("${datasource.database.password}") String password) {
		final var dataSource = new PGConnectionPoolDataSource();
		dataSource.setServerNames(new String[]{serverName});
		dataSource.setPortNumbers(new int[]{port});
		dataSource.setDatabaseName(databaseName);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
