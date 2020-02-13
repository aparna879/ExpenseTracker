package com.ojasa.reimburseit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.ExpenseRequestDaoImplement;
import com.ojasa.reimburseit.model.ExpenseRequest;

@Service
public class GetExpenseRequestService {

	@Autowired
	ExpenseRequestDaoImplement expenseRequest;

	public List<ExpenseRequest> getAllExpenseRequest(int empId) {
		expenseRequest.setJdbcTemplate();
		System.out.println("serviceee");
		return expenseRequest.getAllExpenseRequest(empId);
	}

}
