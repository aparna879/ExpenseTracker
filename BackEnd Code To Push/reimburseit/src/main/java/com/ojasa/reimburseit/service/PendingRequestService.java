package com.ojasa.reimburseit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.LoginDaoImplement;
import com.ojasa.reimburseit.dao.PendingRequestDaoImplementation;
import com.ojasa.reimburseit.model.ApprovedBy;

@Service
public class PendingRequestService {

	@Autowired
	LoginDaoImplement loginDao;

	@Autowired
	PendingRequestDaoImplementation pendingrequestdaoimplementation;

	public List<Integer> financeGetStatus() {
		List<Integer> reqToBeApproved = new ArrayList<>();
		Map<Integer, Integer> reqIdStatus = pendingrequestdaoimplementation.financeListExpReqStatus();
		for (Map.Entry<Integer, Integer> entry : reqIdStatus.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if (value == 4) {
				reqToBeApproved.add(key);
			}
		}
		return reqToBeApproved;
	}

	public List<Integer> getStatus(int empId, String empDesig, String empEmail) {
		List<Integer> reqToBeApproved = new ArrayList<>();
		Map<String, String> mapdesig = new HashMap<>();
		mapdesig.put("Junior", "junior");
		mapdesig.put("Project Manager", "pm");
		mapdesig.put("Human Resource Manager", "hr");
		mapdesig.put("Business Unit Head", "buh");
		mapdesig.put("Finance Team", "ft");

		LoginDaoImplement.setJdbcTemplate();
		List<Integer> empidList = pendingrequestdaoimplementation.listEmpId(empDesig, empEmail);
		for (int i = 0; i < empidList.size(); i++) {
			List<Integer> expReqIdList = pendingrequestdaoimplementation.listExpReq(empidList.get(i));
			for (int j = 0; j < expReqIdList.size(); j++) {
				Map<String, Integer> approvedMap = new HashMap<String, Integer>();
				ApprovedBy approved = pendingrequestdaoimplementation.listApproved(expReqIdList.get(j));
				approvedMap.put("junior", approved.getJunior());
				approvedMap.put("pm", approved.getPm());
				approvedMap.put("hr", approved.getHr());
				approvedMap.put("buh", approved.getBuh());

				String categName = pendingrequestdaoimplementation.categName(expReqIdList.get(j));
				TreeMap<Integer, String> reqFlowMap = pendingrequestdaoimplementation.reqFlow(categName);
				LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>();

				for (Map.Entry<Integer, String> entry : reqFlowMap.entrySet()) {
					Integer key = entry.getKey();
					String value = entry.getValue();
					lhm.put(value, approvedMap.get(value));
				}

				int currentDesig = 0;
				for (Map.Entry<Integer, String> entry : reqFlowMap.entrySet()) {
					Integer key = entry.getKey();
					String value = entry.getValue();
					if (value.equalsIgnoreCase(mapdesig.get(empDesig))) {
						currentDesig = key;
					}
				}

				int prevDone = 0;
				try {
					prevDone = lhm.get(reqFlowMap.get(currentDesig - 1));
				} catch (Exception e) {
					prevDone = 0;
				}
				int currentDone = lhm.get(mapdesig.get(empDesig));
				if (prevDone == 1 && currentDone == 0) {
					reqToBeApproved.add(expReqIdList.get(j));
				}

			}
		}
		return reqToBeApproved;
	}
}
