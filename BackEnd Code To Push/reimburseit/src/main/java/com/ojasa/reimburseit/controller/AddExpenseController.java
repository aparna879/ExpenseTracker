package com.ojasa.reimburseit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ojasa.reimburseit.model.ExpenseRequest;
import com.ojasa.reimburseit.service.AddExpenseService;

@RestController
@RequestMapping("/expense")
public class AddExpenseController {

	@Autowired
	AddExpenseService addexpenseservice;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public String add(@RequestParam("filesToSubmit[]") MultipartFile[] filesArr, @RequestParam("data") String expReqDet)
			throws Exception {
		Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		ExpenseRequest expenseRequest = g.fromJson(expReqDet, ExpenseRequest.class);
		addexpenseservice.addexpense(expenseRequest, filesArr);
		return "Added Successfully";
	}
}
