package com.ojasa.reimburseit.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojasa.reimburseit.model.BarChartParameters;
import com.ojasa.reimburseit.service.TestService;

@RestController
public class TestController
{
	
	@Autowired
	TestService testservice;
	
	@GetMapping("/test")
	public String test() {
		return "TEST SUCCESS";
	}
	
	@GetMapping("/piechart")
	public Map<String,Integer> testchart() {
		return testservice.getchartvalue();
	}
	
	@GetMapping("/barchart")
	public List<BarChartParameters> barchart() {
		return testservice.getbarchart();
	}
}
