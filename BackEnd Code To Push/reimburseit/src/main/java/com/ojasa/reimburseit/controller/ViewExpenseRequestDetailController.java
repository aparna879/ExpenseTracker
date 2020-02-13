package com.ojasa.reimburseit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojasa.reimburseit.model.ExpenseRequestDetail;
import com.ojasa.reimburseit.service.GetExpenseRequestDetailService;

@RestController
@RequestMapping("/expensedetails")
public class ViewExpenseRequestDetailController {

	@Autowired
	GetExpenseRequestDetailService expenseRequestDetail;

	@GetMapping("/views")
	public List<ExpenseRequestDetail> getAllExpenseRequestDetail(@RequestParam("expReqId") int expReqId) {
		return expenseRequestDetail.getAllExpenseRequestDetail(expReqId);
	}

	@GetMapping("/view")
	public String returnSomething() {
		return "returned Something";
	}

}