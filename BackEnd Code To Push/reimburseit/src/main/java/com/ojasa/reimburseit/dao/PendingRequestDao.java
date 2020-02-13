package com.ojasa.reimburseit.dao;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ojasa.reimburseit.model.ApprovedBy;

public interface PendingRequestDao {
	public List<Integer> listEmpId(String empDesig, String empEmail);

	public List<Integer> listExpReq(int empId);

	public ApprovedBy listApproved(int expReqId);

	public String categName(int expReqId);

	public TreeMap<Integer, String> reqFlow(String catName);

	public Map<Integer, Integer> financeListExpReqStatus();

}
