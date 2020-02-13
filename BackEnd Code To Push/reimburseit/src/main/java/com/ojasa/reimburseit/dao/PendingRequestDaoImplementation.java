package com.ojasa.reimburseit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.ApprovedBy;
import com.ojasa.reimburseit.rowmapperlambda.ApprovedByRowMapper;

@Repository
public class PendingRequestDaoImplementation implements PendingRequestDao {

	public List<Integer> listEmpId(String empDesig, String empEmail) {
		Map<String, String> mapdesig = new HashMap<String, String>();
		mapdesig.put("Project Manager", "emp_pm_email");
		mapdesig.put("Human Resource Manager", "emp_hr_email");
		mapdesig.put("Business Unit Head", "emp_buh_email");

		String query = "select emp_id from employee where " + mapdesig.get(empDesig) + "='" + empEmail + "'";

		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				List<Integer> list = new ArrayList<Integer>();
				while (rs.next()) {
					int id;
					id = rs.getInt("emp_id");
					list.add(id);
				}
				return list;
			}
		});
	}

	public List<Integer> listExpReq(int empId) {
		String query = "select exp_req_id from expense_request where emp_id=" + empId;

		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				List<Integer> list = new ArrayList<Integer>();
				while (rs.next()) {
					int id;
					id = rs.getInt("exp_req_id");
					list.add(id);
				}
				return list;
			}
		});
	}

	public ApprovedBy listApproved(int expReqId) {
		String query = "select * from approved_by where exp_req_id=" + expReqId;

		List<ApprovedBy> approved = LoginDaoImplement.jdbcTemplate.query(query,
				ApprovedByRowMapper.ApprovedByRowMapperLambda);
		return approved.get(0);
	}

	public String categName(int expReqId) {
		String query = "select exp_req_category from expense_request where exp_req_id=" + expReqId;

		List<String> list = LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<String>();
				while (rs.next()) {
					String catName;
					catName = rs.getString("exp_req_category");
					list.add(catName);
				}
				return list;
			}
		});
		return list.get(0);
	}

	public TreeMap<Integer, String> reqFlow(String catName) {
		String query = "select desgn_junior,desgn_pm,desgn_hr,desgn_buh from request_flow where category_name ='"
				+ catName + "'";

		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<TreeMap<Integer, String>>() {
			public TreeMap<Integer, String> extractData(ResultSet rs) throws SQLException {
				TreeMap<Integer, String> reqFlowMap = new TreeMap<Integer, String>();
				while (rs.next()) {
					reqFlowMap.put(rs.getInt("desgn_junior"), "junior");
					reqFlowMap.put(rs.getInt("desgn_pm"), "pm");
					reqFlowMap.put(rs.getInt("desgn_hr"), "hr");
					reqFlowMap.put(rs.getInt("desgn_buh"), "buh");
				}
				return reqFlowMap;
			}
		});
	}

	public Map<Integer, Integer> financeListExpReqStatus() {
		String query = "select exp_req_id,exp_req_status from expense_request";

		return LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<Map<Integer, Integer>>() {
			public Map<Integer, Integer> extractData(ResultSet rs) throws SQLException {
				Map<Integer, Integer> list = new HashMap<Integer, Integer>();
				while (rs.next()) {
					int id;
					id = rs.getInt("exp_req_id");
					int status;
					status = rs.getInt("exp_req_status");
					list.put(id, status);
				}
				return list;
			}
		});
	}

}
