package com.ojasa.reimburseit.dao;

import com.ojasa.reimburseit.model.Employee;
import com.ojasa.reimburseit.model.Login;

public interface LoginDao {

	public Employee validateDao(Login loginObj);
}
