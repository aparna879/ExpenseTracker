package com.ojasa.reimburseit.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.Employee;
import com.ojasa.reimburseit.model.Login;
import com.ojasa.reimburseit.rowmapperlambda.RowMapperLambda;

@Repository
public class LoginDaoImplement implements LoginDao {
	private static LoginDaoImplement singleInstance = null;

	public static NamedParameterJdbcTemplate jdbcTemplate;

	private LoginDaoImplement() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/reimburse_it?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public static LoginDaoImplement setJdbcTemplate() {
		if (singleInstance == null)
			singleInstance = new LoginDaoImplement();
		return singleInstance;
	}

	public Employee validateDao(Login loginobj) {
		String[] loginCred = new String[2];

		loginCred[0] = loginobj.email;
		loginCred[1] = loginobj.password;
		String query = "select * from employee where emp_email ='" + loginCred[0] + "' and emp_password='"
				+ loginCred[1] + "'";

		try {
			ArrayList<Employee> list = (ArrayList<Employee>) jdbcTemplate.query(query,
					RowMapperLambda.employeeRowMapperLambda);
			return list.get(0);
		} catch (Exception e) {
			return new Employee();
		}
	}

	public List<Employee> getallEmployee() {
		return jdbcTemplate.query("select * from employee", RowMapperLambda.employeeRowMapperLambda);
	}
}

