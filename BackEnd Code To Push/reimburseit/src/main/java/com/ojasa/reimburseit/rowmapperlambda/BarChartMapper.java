package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.BarChartParameters;

public class BarChartMapper {
	public static final RowMapper<BarChartParameters> barchartRowMapperLambda = (rs, rownum) -> {
		BarChartParameters barparameters = new BarChartParameters();
		barparameters.setFirstName(rs.getString("emp_first_name"));
		barparameters.setLastName(rs.getString("emp_last_name"));
		barparameters.setTotalCost(rs.getInt("TotalCost"));
		return barparameters;
	};
}

