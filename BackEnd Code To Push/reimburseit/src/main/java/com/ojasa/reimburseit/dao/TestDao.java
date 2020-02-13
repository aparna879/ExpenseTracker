package com.ojasa.reimburseit.dao;

import java.util.List;
import java.util.Map;

import com.ojasa.reimburseit.model.BarChartParameters;

public interface TestDao {
	public Map<String, Integer> getchartvalue();

	public List<BarChartParameters> getbarchart();
}
