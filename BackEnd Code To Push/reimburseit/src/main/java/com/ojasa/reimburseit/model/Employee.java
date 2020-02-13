package com.ojasa.reimburseit.model;

public class Employee {
	private int empId;
	private String empFirstName;
	private String empLastName;
	private String empDesignation;
	private String empEmail;
	private String empPassword;
	private String empBuhEmail;
	private String empHrEmail;
	private String empPmEmail;

	public Employee() {

	}

	public Employee(int empId, String empFirstName, String empLastName, String empDesignation, String empEmail,
			String empPassword, String empBuhEmail, String empHrEmail, String empPmEmail) {
		super();
		this.empId = empId;
		this.empFirstName = empFirstName;
		this.empLastName = empLastName;
		this.empDesignation = empDesignation;
		this.empEmail = empEmail;
		this.empPassword = empPassword;
		this.empBuhEmail = empBuhEmail;
		this.empHrEmail = empHrEmail;
		this.empPmEmail = empPmEmail;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}

	public String getEmpBuhEmail() {
		return empBuhEmail;
	}

	public void setEmpBuhEmail(String empBuhEmail) {
		this.empBuhEmail = empBuhEmail;
	}

	public String getEmpHrEmail() {
		return empHrEmail;
	}

	public void setEmpHrEmail(String empHrEmail) {
		this.empHrEmail = empHrEmail;
	}

	public String getEmpPmEmail() {
		return empPmEmail;
	}

	public void setEmpPmEmail(String empPmEmail) {
		this.empPmEmail = empPmEmail;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empFirstName=" + empFirstName + ", empLastName=" + empLastName
				+ ", empDesignation=" + empDesignation + ", empEmail=" + empEmail + ", empPassword=" + empPassword
				+ ", empBuhEmail=" + empBuhEmail + ", empHrEmail=" + empHrEmail + ", empPmEmail=" + empPmEmail + "]";
	}

}
