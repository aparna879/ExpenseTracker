package com.ojasa.reimburseit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojasa.reimburseit.model.ExpenseRequest;
import com.ojasa.reimburseit.service.GetExpenseRequestService;

@RestController
@RequestMapping("/viewexpenses")
public class ViewExpenseRequestsController {
	
	@Autowired
	GetExpenseRequestService expenseRequest;
	
	@GetMapping("/view")
	public List<ExpenseRequest> getAllExpenseRequest(@RequestParam("empId") int empId) {
		return expenseRequest.getAllExpenseRequest(empId);
	}
	
	
}