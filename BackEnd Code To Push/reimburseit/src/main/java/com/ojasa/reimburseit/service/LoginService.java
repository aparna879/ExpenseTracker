package com.ojasa.reimburseit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.LoginDaoImplement;
import com.ojasa.reimburseit.model.Employee;
import com.ojasa.reimburseit.model.Login;

@Service
public class LoginService {
	@Autowired
	LoginDaoImplement loginDao;

	public Employee validateService(Login loginobj) {
		loginDao.setJdbcTemplate();
		return loginDao.validateDao(loginobj);
	}

	public List<Employee> getallEmployee() {
		return (ArrayList<Employee>) loginDao.getallEmployee();
	}
}

