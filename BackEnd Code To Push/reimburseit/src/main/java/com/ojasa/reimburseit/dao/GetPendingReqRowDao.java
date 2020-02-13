package com.ojasa.reimburseit.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ojasa.reimburseit.model.PendingReqRow;
import com.ojasa.reimburseit.rowmapperlambda.PendingReqRowMapper;

@Repository
public class GetPendingReqRowDao implements GetPendingReqRowDaoInterface {
	public PendingReqRow getPendingReqRow(int expReqId) {
		LoginDaoImplement.setJdbcTemplate();
		String query = "Select exp_req_id,exp_req_category,exp_req_date,exp_req_total_cost from expense_request where exp_req_id="
				+ expReqId;
		List<PendingReqRow> reqrow = LoginDaoImplement.jdbcTemplate.query(query,
				PendingReqRowMapper.PendingReqRowRowMapperLambda);
		return reqrow.get(0);
	}
}

