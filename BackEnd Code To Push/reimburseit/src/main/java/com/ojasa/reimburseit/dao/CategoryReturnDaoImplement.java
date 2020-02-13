package com.ojasa.reimburseit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryReturnDaoImplement implements CategoryReturnDao {

	public List<String> sendcategory() {
		LoginDaoImplement.setJdbcTemplate();
		String query = "select category_name from request_flow";
		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<String>();
				while (rs.next()) {
					String category = new String();
					category = rs.getString("category_name");
					list.add(category);
				}
				return list;
			}
		});
	}
}

