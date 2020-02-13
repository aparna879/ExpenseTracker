package com.ojasa.reimburseit.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PendingReqRow {
	private int expReqId;

	private String category;
	private int total;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	public PendingReqRow() {
	}

	public PendingReqRow(int expReqId, String category, int total, Date date) {
		super();
		this.expReqId = expReqId;
		this.category = category;
		this.total = total;
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getExpReqId() {
		return expReqId;
	}

	public void setExpReqId(int expReqId) {
		this.expReqId = expReqId;
	}

	@Override
	public String toString() {
		return "PendingReqRow [expReqId=" + expReqId + ", category=" + category + ", total=" + total + ", date=" + date
				+ "]";
	}

}
