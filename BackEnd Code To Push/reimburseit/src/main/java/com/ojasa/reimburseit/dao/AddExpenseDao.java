package com.ojasa.reimburseit.dao;

import org.springframework.web.multipart.MultipartFile;
import com.ojasa.reimburseit.model.EmailParameters;
import com.ojasa.reimburseit.model.ExpenseRequest;

public interface AddExpenseDao {
	public EmailParameters addexpense(ExpenseRequest expenseobj, MultipartFile[] filesArr);

	public int getrequestid(ExpenseRequest expenseobj);

	public String getEmployeename(ExpenseRequest expenseobj);
}
