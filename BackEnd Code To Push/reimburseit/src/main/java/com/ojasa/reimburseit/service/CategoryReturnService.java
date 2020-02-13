package com.ojasa.reimburseit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.CategoryReturnDaoImplement;
import com.ojasa.reimburseit.dao.LoginDaoImplement;

@Service
public class CategoryReturnService {

	@Autowired
	CategoryReturnDaoImplement categoryreturndaoimplement;

	public List<String> sendcategory() {
		LoginDaoImplement.setJdbcTemplate();
		return categoryreturndaoimplement.sendcategory();
	}
}

