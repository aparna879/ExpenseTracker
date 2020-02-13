package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.PendingReqRow;

public class PendingReqRowMapper {
	public static final RowMapper<PendingReqRow> PendingReqRowRowMapperLambda = (rs, rownum) -> {
		PendingReqRow pendingReqRow = new PendingReqRow();
		pendingReqRow.setExpReqId(rs.getInt("exp_req_id"));
		pendingReqRow.setCategory(rs.getString("exp_req_category"));
		pendingReqRow.setDate(rs.getDate("exp_req_date"));
		pendingReqRow.setTotal(rs.getInt("exp_req_total_cost"));

		return pendingReqRow;
	};
}

