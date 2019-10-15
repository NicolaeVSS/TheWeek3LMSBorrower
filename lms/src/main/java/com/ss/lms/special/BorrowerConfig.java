package com.ss.lms.special;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import com.ss.lms.dao.*;

@Configuration
public class BorrowerConfig {

	
	public String driverName = "com.mysql.cj.jdbc.Driver";
	public String url = "jdbc:mysql://127.0.0.1:3306/library?useTimezone=true&serverTimezone=UTC";	
	public String username = "root";
	public String password = "Bijon128";

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource =  new BasicDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	@Qualifier(value="mysqlTemplate")
	public JdbcTemplate mysqlTemplate(){
		return new JdbcTemplate(getDataSource());
	}
	
	@Bean	
	public BookCopyDao bookCopy(){
		return new BookCopyDao(getDataSource());
	}
	
	@Bean
	public BookloanDao daoBookloan(){
		return new BookloanDao(getDataSource());
	}
	
	@Bean
	DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(getDataSource());
	}
}
