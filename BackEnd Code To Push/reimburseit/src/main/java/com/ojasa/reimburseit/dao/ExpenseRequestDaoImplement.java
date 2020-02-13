package com.ojasa.reimburseit.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.ExpenseRequest;
import com.ojasa.reimburseit.rowmapperlambda.ExpenseRequestRowMapper;

@Repository
public class ExpenseRequestDaoImplement implements ExpenseRequestDao {
	private static ExpenseRequestDaoImplement singleInstance = null;
	public static NamedParameterJdbcTemplate jdbcTemplate;

	public ExpenseRequestDaoImplement() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/reimburse_it?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public static ExpenseRequestDaoImplement setJdbcTemplate() {
		if (singleInstance == null)
			singleInstance = new ExpenseRequestDaoImplement();
		return singleInstance;
	}

	public List<ExpenseRequest> getAllExpenseRequest(int empId) {
		return jdbcTemplate.query("select * from expense_request where emp_id=" + empId,
				ExpenseRequestRowMapper.ExpenseRequestRowMapperLambda);
	}

}

