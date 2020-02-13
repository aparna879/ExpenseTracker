package com.ojasa.reimburseit.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.ojasa.reimburseit.model.EmailParameters;
import com.ojasa.reimburseit.model.ExpenseRequest;
import com.ojasa.reimburseit.service.GetImageService;

@Repository
public class AddExpenseDaoImplement implements AddExpenseDao {

	@Autowired
	GetImageService getImageService;

	public int getrequestid(ExpenseRequest expenseobj) {
		LoginDaoImplement.setJdbcTemplate();
		String query2 = "select exp_req_id from expense_request where exp_req_category='" + expenseobj.getCategoryname()
				+ "' and emp_id=" + expenseobj.getEmpid() + " and exp_req_date='" + expenseobj.getCurrentdate()
				+ "' and exp_req_description='" + expenseobj.getDescription() + "' and exp_req_total_cost="
				+ expenseobj.getTotalcost();
		List<Integer> list = LoginDaoImplement.jdbcTemplate.query(query2, new ResultSetExtractor<List<Integer>>() {
			public List<Integer> extractData(ResultSet rs) throws SQLException {
				List<Integer> list = new ArrayList<>();
				while (rs.next()) {
					int id;
					id = rs.getInt("exp_req_id");
					list.add(id);
				}
				return list;
			}
		});
		return (int) list.get(0);
	}

	public String getEmployeename(ExpenseRequest expenseobj) {
		LoginDaoImplement.setJdbcTemplate();
		String query4 = "select emp_first_name from employee where emp_id=" + expenseobj.getEmpid();
		List<String> list = LoginDaoImplement.jdbcTemplate.query(query4, new ResultSetExtractor<List<String>>() {
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
		return (String) list.get(0);
	}

	public String getEmployeerole(ExpenseRequest expenseobj) {
		LoginDaoImplement.setJdbcTemplate();
		String query5 = "select emp_designation from employee where emp_id='" + expenseobj.getEmpid() + "'";
		List<String> list = LoginDaoImplement.jdbcTemplate.query(query5, new ResultSetExtractor<List<String>>() {
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
		return list.get(0);
	}

	public EmailParameters addexpense(ExpenseRequest expenseobj, MultipartFile[] filesArr) {
		LoginDaoImplement.setJdbcTemplate();
		SqlParameterSource parameters1 = new MapSqlParameterSource();
		((MapSqlParameterSource) parameters1).addValue("categoryname", expenseobj.getCategoryname());
		((MapSqlParameterSource) parameters1).addValue("empid", expenseobj.getEmpid());
		((MapSqlParameterSource) parameters1).addValue("date", expenseobj.getCurrentdate());
		((MapSqlParameterSource) parameters1).addValue("description", expenseobj.getDescription());
		((MapSqlParameterSource) parameters1).addValue("totalcost", expenseobj.getTotalcost());
		String query1 = "insert into expense_request(exp_req_category,emp_id ,exp_req_date,exp_req_description,exp_req_total_cost) values(:categoryname,:empid,:date,:description,:totalcost)";
		LoginDaoImplement.jdbcTemplate.update(query1, parameters1);

		int requestid = getrequestid(expenseobj);

		SqlParameterSource parameters2 = new MapSqlParameterSource();
		for (int i = 0; i < expenseobj.getListdetails().size(); i++) {
			((MapSqlParameterSource) parameters2).addValue("expenserequestid", requestid);
			((MapSqlParameterSource) parameters2).addValue("expensename",
					expenseobj.getListdetails().get(i).getExpensename());
			((MapSqlParameterSource) parameters2).addValue("expensecost",
					expenseobj.getListdetails().get(i).getExpensecost());
			((MapSqlParameterSource) parameters2).addValue("expensedate",
					expenseobj.getListdetails().get(i).getExpensedate());

			MultipartFile file = filesArr[i];
			byte[] bill = new byte[(int) file.getSize()];
			try {
				bill = file.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			((MapSqlParameterSource) parameters2).addValue("expensefile", bill);

			String query3 = "insert into expense_detail(exp_req_id,exp_det_name,exp_det_cost,exp_det_date_of_expenditure,exp_det_proof) values(:expenserequestid,:expensename,:expensecost,:expensedate,:expensefile)";
			LoginDaoImplement.jdbcTemplate.update(query3, parameters2);
		}
		getImageService.fetchimage(requestid);

		SqlParameterSource parameters3 = new MapSqlParameterSource().addValue("Requestid", requestid)
				.addValue("param1", 1).addValue("param2", 0).addValue("param3", 0).addValue("param4", 0);
		String query9 = "insert into approved_by values(:Requestid,:param1,:param2,:param3,:param4)";
		LoginDaoImplement.jdbcTemplate.update(query9, parameters3);

		// Sending Parameters for Sending Email
		List<String> roles = new ArrayList<>();
		roles.add("Junior");
		roles.add("Project Manager");
		roles.add("Human Resource Manager");
		roles.add("Business Unit Head");

		String query6 = "select desgn_junior,desgn_pm,desgn_hr,desgn_buh from request_flow where category_name ='"
				+ expenseobj.getCategoryname() + "'";
		TreeMap<Integer, String> resultmap = LoginDaoImplement.jdbcTemplate.query(query6,
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
		String emprole = getEmployeerole(expenseobj);
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
			String query7 = null;
			List<String> list = new ArrayList<>();

			Map<Character, String> mapdesig = new HashMap<>();

			mapdesig.put('P', "emp_pm_email");
			mapdesig.put('H', "emp_hr_email");
			mapdesig.put('B', "emp_buh_email");
			if (!emprole.equals("Business Unit Head")) {
				query7 = "select " + mapdesig.get(firstchar) + " from employee where emp_id='" + expenseobj.getEmpid()
						+ "'";
				list = LoginDaoImplement.jdbcTemplate.query(query7, new ResultSetExtractor<List<String>>() {
					public List<String> extractData(ResultSet rs) throws SQLException {
						List<String> list = new ArrayList<>();
						while (rs.next()) {
							String approveremail;
							approveremail = rs.getString(mapdesig.get(firstchar));
							list.add(approveremail);
						}
						return list;
					}
				});
				email = list.get(0);
			}
		}

		EmailParameters emailparam = new EmailParameters();
		emailparam.setEmpname(getEmployeename(expenseobj));
		emailparam.setExpenserequestid(requestid);
		emailparam.setApproveremail(email);
		return emailparam;
	}
}

