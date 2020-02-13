package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;
import com.ojasa.reimburseit.model.ExpenseRequestDetail;

public class ExpenseRequestDetailRowMapper {

	public static final RowMapper<ExpenseRequestDetail> ExpenseRequestDetailRowMapperLambda = (rs, rownum) -> {
		ExpenseRequestDetail expenseRequest = new ExpenseRequestDetail();

		expenseRequest.setExpensename(rs.getString("exp_det_name"));
		expenseRequest.setExpensecost(rs.getInt("exp_det_cost"));
		expenseRequest.setExpensedate(rs.getDate("exp_det_date_of_expenditure"));
		return expenseRequest;
	};
}
