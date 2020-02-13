package com.ojasa.reimburseit.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EmailParameters {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date currentdate;
	private int expenserequestid;
	private int empid;
	private String empname;
	private String approveremail;
	private int approverid;
	private String requesterrole;
	private String requestername;
	private String expensecategory;
	private int totalcost;
	private int length;

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getCurrentdate() {
		return currentdate;
	}

	public void setCurrentdate(Date currentdate) {
		this.currentdate = currentdate;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getRequesterrole() {
		return requesterrole;
	}

	public void setRequesterrole(String requesterrole) {
		this.requesterrole = requesterrole;
	}

	public String getRequestername() {
		return requestername;
	}

	public void setRequestername(String requestername) {
		this.requestername = requestername;
	}

	public String getExpensecategory() {
		return expensecategory;
	}

	public void setExpensecategory(String expensecategory) {
		this.expensecategory = expensecategory;
	}

	public int getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}

	public int getExpenserequestid() {
		return expenserequestid;
	}

	public void setExpenserequestid(int expenserequestid) {
		this.expenserequestid = expenserequestid;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getApproveremail() {
		return approveremail;
	}

	public void setApproveremail(String approveremail) {
		this.approveremail = approveremail;
	}

	public int getApproverid() {
		return approverid;
	}

	public void setApproverid(int approverid) {
		this.approverid = approverid;
	}
}
