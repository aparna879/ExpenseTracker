package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.Employee;

public class RowMapperLambda {

	private RowMapperLambda() {
	}

	public static final RowMapper<Employee> employeeRowMapperLambda = (rs, rownum) -> {
		Employee employee = new Employee();
		employee.setEmpId(rs.getInt("emp_id"));
		employee.setEmpFirstName(rs.getString("emp_first_name"));
		employee.setEmpLastName(rs.getString("emp_last_name"));
		employee.setEmpDesignation(rs.getString("emp_designation"));
		employee.setEmpEmail(rs.getString("emp_email"));
		employee.setEmpPassword(rs.getString("emp_password"));
		employee.setEmpBuhEmail(rs.getString("emp_buh_email"));
		employee.setEmpHrEmail(rs.getString("emp_hr_email"));
		employee.setEmpPmEmail(rs.getString("emp_pm_email"));
		return employee;
	};
}

