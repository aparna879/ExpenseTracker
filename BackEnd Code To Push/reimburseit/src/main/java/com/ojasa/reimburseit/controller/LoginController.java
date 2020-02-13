package com.ojasa.reimburseit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojasa.reimburseit.model.Employee;
import com.ojasa.reimburseit.model.Login;
import com.ojasa.reimburseit.service.LoginService;

@RestController
@RequestMapping("/reimburse")
public class LoginController {

	@Autowired
	LoginService loginservice;

	@GetMapping("/view")
	public List<Employee> getEmployee() {
		return loginservice.getallEmployee();
	}

	@PostMapping("/validate")
	public Employee validate(@RequestBody Login tempLoginObject) {
		return loginservice.validateService(tempLoginObject);
	}

}
