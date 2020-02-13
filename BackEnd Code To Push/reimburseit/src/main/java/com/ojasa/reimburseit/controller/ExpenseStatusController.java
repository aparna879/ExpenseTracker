package com.ojasa.reimburseit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ojasa.reimburseit.service.ExpenseStatusService;

@RestController
@RequestMapping("/approval")
public class ExpenseStatusController {

	@Autowired
	ExpenseStatusService expensestatusservice;

	@GetMapping("/accept")
	@ResponseStatus(HttpStatus.OK)
	public Map<Integer, String> updateacceptstatus(@RequestParam("ExpReqId") int expReqId,
			@RequestParam("ApproverEmail") String approveremail, @RequestParam("ExpStatus") String expstatus)
			throws Exception {
		String str;
		if (approveremail.equals("jaspreet.singh@accoliteindia.com")) {
			str = expensestatusservice.ftacceptservice(expReqId);
		} else {
			str = expensestatusservice.updateacceptstatus(expReqId, approveremail, expstatus);
		}
		Map<Integer, String> map = new HashMap<>();
		map.put(1, str);
		return map;
	}

	@GetMapping("/reject")
	@ResponseStatus(HttpStatus.OK)
	public Map<Integer, String> updaterejectstatus(@RequestParam("ExpReqId") int expReqId,
			@RequestParam("ApproverEmail") String approveremail, @RequestParam("ExpStatus") String expstatus) {
		String str;
		if (approveremail.equals("jaspreet.singh@accoliteindia.com")) {
			str = expensestatusservice.ftrejectservice(expReqId);
		} else {
			str = expensestatusservice.updaterejectstatus(expReqId, approveremail, expstatus);
		}
		Map<Integer, String> map = new HashMap<>();
		map.put(1, str);
		return map;
	}
}
