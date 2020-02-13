package com.ojasa.reimburseit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.BarChartParameters;
import com.ojasa.reimburseit.rowmapperlambda.BarChartMapper;

@Repository
public class TestDaoImplement implements TestDao {

	public Map<String, Integer> getchartvalue() {
		LoginDaoImplement.setJdbcTemplate();
		String query1 = "select exp_req_category,sum(exp_req_total_cost) as total_cost from expense_request group by exp_req_category";

		return (Map<String, Integer>) LoginDaoImplement.jdbcTemplate.query(query1,
				new ResultSetExtractor<Map<String, Integer>>() {
					public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
						Map<String, Integer> map = new HashMap<>();
						while (rs.next()) {
							String str;
							int totalcost;
							str = rs.getString("exp_req_category");
							totalcost = rs.getInt("total_cost");
							map.put(str, totalcost);
						}
						return map;
					}
				});
	}

	public List<BarChartParameters> getbarchart() {
		LoginDaoImplement.setJdbcTemplate();
		String query2 = "select a.emp_first_name, a.emp_last_name, sum(b.exp_req_total_cost) as TotalCost from employee a, expense_request b where a.emp_id = b.emp_id group by b.emp_id";
		return LoginDaoImplement.jdbcTemplate.query(query2, BarChartMapper.barchartRowMapperLambda);
	}

}

