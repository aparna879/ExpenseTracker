package com.ojasa.reimburseit.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.TestDaoImplement;
import com.ojasa.reimburseit.model.BarChartParameters;

@Service
public class TestService {

	@Autowired
	TestDaoImplement testdao;

	public Map<String, Integer> getchartvalue() {
		return testdao.getchartvalue();
	}

	public List<BarChartParameters> getbarchart() {
		return testdao.getbarchart();
	}
}

