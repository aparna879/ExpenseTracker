package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.ApprovedBy;

public class ApprovedByRowMapper {
	public static final RowMapper<ApprovedBy> ApprovedByRowMapperLambda = (rs, rownum) -> {
		ApprovedBy approvedBy = new ApprovedBy();

		approvedBy.setExpReqId(rs.getInt("exp_req_id"));
		approvedBy.setJunior(rs.getInt("desgn_junior"));
		approvedBy.setHr(rs.getInt("desgn_hr"));
		approvedBy.setPm(rs.getInt("desgn_pm"));
		approvedBy.setBuh(rs.getInt("desgn_buh"));

		return approvedBy;
	};
}

