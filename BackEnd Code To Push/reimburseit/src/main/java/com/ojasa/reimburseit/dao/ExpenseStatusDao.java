package com.ojasa.reimburseit.dao;

import com.ojasa.reimburseit.model.EmailParameters;
import com.ojasa.reimburseit.model.ExpenseRequest;

public interface ExpenseStatusDao {
	public EmailParameters updateacceptstatus(int expReqId, String approveremail, String expstatus);

	public String updaterejectstatus(int expReqId, String approveremail, String expstatus);

	public String getdesignation(String approveremail);

	public int getStatusIndex(String status);

	public ExpenseRequest getdetails(int expReqId);

	public String getname(int empId);

	public String getrequestorname(String approveremail);
}
