package com.ojasa.reimburseit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.ExpenseRequestDetailDaoImplement;
import com.ojasa.reimburseit.model.ExpenseRequestDetail;

@Service
public class GetExpenseRequestDetailService {

	@Autowired
	ExpenseRequestDetailDaoImplement expenseRequestDetail;

	public List<ExpenseRequestDetail> getAllExpenseRequestDetail(int expReqId) {
		return expenseRequestDetail.getExpenseRequestDetail(expReqId);
	}
}
