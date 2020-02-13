package com.ojasa.reimburseit.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.ExpenseRequestDetail;
import com.ojasa.reimburseit.rowmapperlambda.ExpenseRequestDetailRowMapper;

@Repository
public class ExpenseRequestDetailDaoImplement {

	private static ExpenseRequestDetailDaoImplement singleInstance = null;
	public static NamedParameterJdbcTemplate jdbcTemplate;

	public ExpenseRequestDetailDaoImplement() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/reimburse_it?useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public static ExpenseRequestDetailDaoImplement setJdbcTemplate() {
		if (singleInstance == null)
			singleInstance = new ExpenseRequestDetailDaoImplement();
		return singleInstance;
	}

	public List<ExpenseRequestDetail> getExpenseRequestDetail(int expReqId) {
		return jdbcTemplate.query("select * from expense_detail where exp_req_id=" + expReqId,
				ExpenseRequestDetailRowMapper.ExpenseRequestDetailRowMapperLambda);
	}
}
