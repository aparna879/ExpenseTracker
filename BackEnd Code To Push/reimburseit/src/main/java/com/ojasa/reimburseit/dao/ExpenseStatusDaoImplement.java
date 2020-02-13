package com.ojasa.reimburseit.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ojasa.reimburseit.model.EmailParameters;
import com.ojasa.reimburseit.model.ExpenseRequest;
import com.ojasa.reimburseit.rowmapperlambda.ExpenseRequestEmailRowMapperLambda;

@Repository
public class ExpenseStatusDaoImplement implements ExpenseStatusDao {

	public int getStatusIndex(String status) {
		String query = "select req_status_id from request_status where req_status_description='" + status + "'";
		List<Integer> resultlist = LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				List<Integer> list = new ArrayList<>();
				while (rs.next()) {
					Integer index;
					index = rs.getInt("req_status_id");
					list.add(index);
				}
				return list;
			}
		});
		return resultlist.remove(0);
	}

	public String getdesignation(String approveremail) {
		String query = "select emp_designation from employee where emp_email='" + approveremail + "'";
		List<String> resultlist = LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<>();
				while (rs.next()) {
					String emprole;
					emprole = rs.getString("emp_designation");
					list.add(emprole);
				}
				return list;
			}
		});
		return resultlist.get(0);
	}

	public String getrequestorname(String approveremail) {
		String query = "select emp_first_name from employee where emp_email='" + approveremail + "'";
		List<String> resultlist = LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<>();
				while (rs.next()) {
					String empname;
					empname = rs.getString("emp_first_name");
					list.add(empname);
				}
				return list;
			}
		});
		return resultlist.get(0);
	}

	public ExpenseRequest getdetails(int expReqId) {
		String query = "select exp_req_category, emp_id, exp_req_date, exp_req_total_cost from expense_request where exp_req_id ='"
				+ expReqId + "'";
		ArrayList<ExpenseRequest> list = (ArrayList<ExpenseRequest>) LoginDaoImplement.jdbcTemplate.query(query,
				ExpenseRequestEmailRowMapperLambda.ExpenseRequestEmailRowMapperLambda);
		return list.get(0);
	}

	public String getname(int empId) {
		String query = "select emp_first_name from employee where emp_id='" + empId + "'";
		List<String> resultlist = LoginDaoImplement.jdbcTemplate.query(query, new ResultSetExtractor<List<String>>() {
			public List<String> extractData(ResultSet rs) throws SQLException {
				List<String> list = new ArrayList<>();
				while (rs.next()) {
					String empname;
					empname = rs.getString("emp_first_name");
					list.add(empname);
				}
				return list;
			}
		});
		return resultlist.get(0);
	}

	public EmailParameters updateacceptstatus(int expReqId, String approveremail, String expstatus) {
		LoginDaoImplement.setJdbcTemplate();
		String empdesignation = getdesignation(approveremail);
		String status = expstatus.substring(0, 8) + " " + "by" + " " + empdesignation;
		int statusindex = getStatusIndex(status);
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ExpReqStatus", statusindex)
				.addValue("ExpReqId", expReqId);
		String query = "update expense_request set  exp_req_status = :ExpReqStatus where  exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query, parameters);
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("Project Manager", "desgn_pm");
		map1.put("Human Resource Manager", "desgn_hr");
		map1.put("Business Unit Head", "desgn_buh");

		String variable = map1.get(empdesignation);
		SqlParameterSource parameters1 = new MapSqlParameterSource().addValue("variable", 1).addValue("ExpReqId",
				expReqId);
		String query11 = "update approved_by set " + variable + " = :variable where exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query11, parameters1);

		// Sending Parameters for Sending Email
		ExpenseRequest expobj = getdetails(expReqId);
		String empname = getname(expobj.getEmpid());

		List<String> roles = new ArrayList<>();
		roles.add("Junior");
		roles.add("Project Manager");
		roles.add("Human Resource Manager");
		roles.add("Business Unit Head");

		String query1 = "select desgn_junior,desgn_pm,desgn_hr,desgn_buh from request_flow where category_name ='"
				+ expobj.getCategoryname() + "'";
		TreeMap<Integer, String> resultmap = LoginDaoImplement.jdbcTemplate.query(query1,
				new ResultSetExtractor<TreeMap<Integer, String>>() {
					public TreeMap<Integer, String> extractData(ResultSet rs) throws SQLException {
						TreeMap<Integer, String> map = new TreeMap<Integer, String>();
						if (rs.next()) {
							int i = 1;
							int index;
							index = rs.getInt(i);
							map.put(index, roles.get(i - 1));
							index = rs.getInt(i + 1);
							map.put(index, roles.get(i));
							index = rs.getInt(i + 2);
							map.put(index, roles.get(i + 1));
							index = rs.getInt(i + 3);
							map.put(index, roles.get(i + 2));
						}
						return map;
					}
				});

		int indexkey = 0;
		String emprole = getdesignation(approveremail);
		Collection entrySet = resultmap.entrySet();
		Iterator it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry me = (Map.Entry) it.next();
			if (me.getValue().equals(emprole)) {
				indexkey = (int) me.getKey();
				break;
			}
		}
		String approver = resultmap.get(indexkey + 1);
		String email = "jaspreet.singh@accoliteindia.com";

		if (approver != null) {
			char firstchar = approver.charAt(0);

			Map<Character, String> mapdesig = new HashMap<>();
			mapdesig.put('P', "emp_pm_email");
			mapdesig.put('H', "emp_hr_email");
			mapdesig.put('B', "emp_buh_email");

			if (!empdesignation.equals("Business Unit Head")) {
				String query2 = "select " + mapdesig.get(firstchar) + " from employee where emp_id='"
						+ expobj.getEmpid() + "'";
				List<String> list = LoginDaoImplement.jdbcTemplate.query(query2,
						new ResultSetExtractor<List<String>>() {
							public List<String> extractData(ResultSet rs) throws SQLException {
								List<String> list = new ArrayList<>();
								while (rs.next()) {
									String nextapproveremail;
									nextapproveremail = rs.getString(mapdesig.get(firstchar));
									list.add(nextapproveremail);
								}
								return list;
							}
						});
				email = list.get(0);
			}
		}
		EmailParameters emailparamobj = new EmailParameters();
		emailparamobj.setRequestername(getrequestorname(approveremail));
		emailparamobj.setRequesterrole(getdesignation(approveremail));
		emailparamobj.setExpensecategory(expobj.getCategoryname());
		emailparamobj.setEmpid(expobj.getEmpid());
		emailparamobj.setCurrentdate(expobj.getCurrentdate());
		emailparamobj.setTotalcost(expobj.getTotalcost());
		emailparamobj.setEmpname(empname);
		emailparamobj.setApproveremail(email);
		emailparamobj.setLength(expobj.getListdetails().size());
		return emailparamobj;
	}

	public String updaterejectstatus(int expReqId, String approveremail, String expstatus) {
		LoginDaoImplement.setJdbcTemplate();
		String empdesignation = getdesignation(approveremail);
		String status = expstatus.substring(0, 8) + " " + "by" + " " + empdesignation;
		int statusindex = getStatusIndex(status);
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ExpReqStatus", statusindex)
				.addValue("ExpReqId", expReqId);
		String query = "update expense_request set  exp_req_status = :ExpReqStatus where  exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query, parameters);

		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("Project Manager", "desgn_pm");
		map1.put("Human Resource Manager", "desgn_hr");
		map1.put("Business Unit Head", "desgn_buh");

		String variable = map1.get(empdesignation);
		SqlParameterSource parameters1 = new MapSqlParameterSource().addValue("variable", -1).addValue("ExpReqId",
				expReqId);
		String query11 = "update approved_by set " + variable + " = :variable where exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query11, parameters1);

		return "Rejected";
	}

	public String ftacceptdao(int expReqId) {
		LoginDaoImplement.setJdbcTemplate();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ExpReqStatus", 5).addValue("ExpReqId",
				expReqId);
		String query = "update expense_request set  exp_req_status = :ExpReqStatus where  exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query, parameters);
		return "Accepted";
	}

	public String ftrejectdao(int expReqId) {
		LoginDaoImplement.setJdbcTemplate();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("ExpReqStatus", 9).addValue("ExpReqId",
				expReqId);
		String query = "update expense_request set  exp_req_status = :ExpReqStatus where  exp_req_id = :ExpReqId";
		LoginDaoImplement.jdbcTemplate.update(query, parameters);
		return "Rejected";
	}
}

