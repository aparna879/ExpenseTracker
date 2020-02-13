package com.ojasa.reimburseit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.mysql.cj.jdbc.Blob;

@Repository
public class GetImageDao {

	public List<Integer> getExpDetailsId(int expReqId) {
		String query = "Select exp_det_id from expense_detail where exp_req_id=" + expReqId;
		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				List<Integer> list = new ArrayList<>();
				while (rs.next()) {
					int id;
					id = rs.getInt("exp_det_id");
					list.add(id);
				}
				return list;
			}
		});

	}
	

	public Blob getBlobImage(int expDetailsId) {
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("exp", expDetailsId);
		String query = "Select exp_det_proof from expense_detail where exp_det_id=:exp";
		return LoginDaoImplement.jdbcTemplate.queryForObject(query, parameters, Blob.class);
	}

}
