package com.ojasa.reimburseit.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseRequest {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date currentdate;
	private int empid;
	private int totalcost;
	private String categoryname;
	private String description;
	private String reason;
	private int status;
	private int expreqid;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getExpreqid() {
		return expreqid;
	}

	public void setExpreqid(int expreqid) {
		this.expreqid = expreqid;
	}

	List<ExpenseRequestDetail> listdetails = new ArrayList<>();

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

	public int getTotalcost() {
		return totalcost;
	}

	public void setTotalcost(int totalcost) {
		this.totalcost = totalcost;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ExpenseRequestDetail> getListdetails() {
		return listdetails;
	}

	public void setListdetails(List<ExpenseRequestDetail> listdetails) {
		this.listdetails = listdetails;
	}

	@Override
	public String toString() {
		return "ExpenseRequest [currentdate=" + currentdate + ", empid=" + empid + ", totalcost=" + totalcost
				+ ", categoryname=" + categoryname + ", description=" + description + ", listdetails=" + listdetails
				+ "]";
	}

}
