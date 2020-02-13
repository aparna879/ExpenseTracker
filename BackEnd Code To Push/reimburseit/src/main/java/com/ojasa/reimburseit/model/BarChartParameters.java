package com.ojasa.reimburseit.model;

public class BarChartParameters {

	private String FirstName;
	private String LastName;
	private int TotalCost;

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public int getTotalCost() {
		return TotalCost;
	}

	public void setTotalCost(int totalCost) {
		TotalCost = totalCost;
	}

	@Override
	public String toString() {
		return "BarChartParameters [FirstName=" + FirstName + ", LastName=" + LastName + ", TotalCost=" + TotalCost
				+ "]";
	}

}
