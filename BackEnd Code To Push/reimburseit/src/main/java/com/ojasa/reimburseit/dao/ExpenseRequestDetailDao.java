package com.ojasa.reimburseit.dao;

import java.util.List;

import com.ojasa.reimburseit.model.ExpenseRequestDetail;

public interface ExpenseRequestDetailDao {
	public List<ExpenseRequestDetail> getExpenseRequestDetail(int expReqId);
}