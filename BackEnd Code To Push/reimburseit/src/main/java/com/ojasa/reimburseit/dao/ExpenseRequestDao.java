package com.ojasa.reimburseit.dao;

import java.util.List;

import com.ojasa.reimburseit.model.ExpenseRequest;

public interface ExpenseRequestDao {
	public List<ExpenseRequest> getAllExpenseRequest(int empid);
}