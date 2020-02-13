package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.ExpenseRequest;

public class ExpenseRequestEmailRowMapperLambda {
	public static final RowMapper<ExpenseRequest> ExpenseRequestEmailRowMapperLambda = (rs, rownum) -> {
		ExpenseRequest expenseRequest = new ExpenseRequest();

		expenseRequest.setCategoryname(rs.getString("exp_req_category"));
		expenseRequest.setEmpid(rs.getInt("emp_id"));
		expenseRequest.setCurrentdate(rs.getDate("exp_req_date"));
		expenseRequest.setTotalcost(rs.getInt("exp_req_total_cost"));
		return expenseRequest;
	};
}

