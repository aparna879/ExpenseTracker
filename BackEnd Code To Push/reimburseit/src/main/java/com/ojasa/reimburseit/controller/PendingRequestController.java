package com.ojasa.reimburseit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ojasa.reimburseit.dao.GetPendingReqRowDao;
import com.ojasa.reimburseit.model.PendingReqRow;
import com.ojasa.reimburseit.service.PendingRequestService;

@RestController
@RequestMapping("/pending")
public class PendingRequestController {
	@Autowired
	PendingRequestService pendingrequestservice;

	@Autowired
	GetPendingReqRowDao getpendingReqRowDao;

	@GetMapping("/getstatus")
	public List<PendingReqRow> getStatus(@RequestParam("empId") int empId, @RequestParam("empDesig") String empDesig,
			@RequestParam("empEmail") String empEmail) {
		List<Integer> reqToBeApproved = new ArrayList<>();
		List<PendingReqRow> pendingReqRowList = new ArrayList<>();
		PendingReqRow obj;
		if (empDesig.equalsIgnoreCase("Finance Team")) {
			reqToBeApproved = pendingrequestservice.financeGetStatus();

		} else {
			reqToBeApproved = pendingrequestservice.getStatus(empId, empDesig, empEmail);
		}
		for (int i = 0; i < reqToBeApproved.size(); i++) {
			obj = getpendingReqRowDao.getPendingReqRow(reqToBeApproved.get(i));
			pendingReqRowList.add(obj);
		}
		return pendingReqRowList;

	}
}
