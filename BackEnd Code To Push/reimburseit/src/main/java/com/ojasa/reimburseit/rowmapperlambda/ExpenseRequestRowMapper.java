package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;
import com.ojasa.reimburseit.model.ExpenseRequest;

public class ExpenseRequestRowMapper {

	public static final RowMapper<ExpenseRequest> ExpenseRequestRowMapperLambda = (rs, rownum) -> {
		ExpenseRequest expenseRequest = new ExpenseRequest();

		expenseRequest.setExpreqid(rs.getInt("exp_req_id"));
		expenseRequest.setCategoryname(rs.getString("exp_req_category"));
		expenseRequest.setEmpid(rs.getInt("emp_id"));
		expenseRequest.setCurrentdate(rs.getDate("exp_req_date"));
		expenseRequest.setDescription(rs.getString("exp_req_description"));
		expenseRequest.setStatus(rs.getInt("exp_req_status"));
		expenseRequest.setReason(rs.getString("exp_req_reason"));
		expenseRequest.setTotalcost(rs.getInt("exp_req_total_cost"));

		return expenseRequest;
	};
}
