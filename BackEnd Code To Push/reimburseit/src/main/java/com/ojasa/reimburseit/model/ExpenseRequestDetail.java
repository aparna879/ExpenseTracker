package com.ojasa.reimburseit.model;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseRequestDetail {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expensedate;
	private String expensename;
	private int expensecost;

	@Override
	public String toString() {
		return "ExpenseRequestDetail [expensedate=" + expensedate + ", expensename=" + expensename + ", expensecost="
				+ expensecost;
	}

	public Date getExpensedate() {
		return expensedate;
	}

	public void setExpensedate(Date expensedate) {
		this.expensedate = expensedate;
	}

	public String getExpensename() {
		return expensename;
	}

	public void setExpensename(String expensename) {
		this.expensename = expensename;
	}

	public int getExpensecost() {
		return expensecost;
	}

	public void setExpensecost(int expensecost) {
		this.expensecost = expensecost;
	}
}
