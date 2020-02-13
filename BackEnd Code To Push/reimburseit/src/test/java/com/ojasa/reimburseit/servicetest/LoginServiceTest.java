package com.ojasa.reimburseit.servicetest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ojasa.reimburseit.dao.LoginDaoImplement;
import com.ojasa.reimburseit.model.Employee;
import com.ojasa.reimburseit.model.Login;
import com.ojasa.reimburseit.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	@InjectMocks
	LoginService loginservice;

	@Mock
	LoginDaoImplement logindaoimplement;

	Login loginobj = new Login();
	Employee emp = new Employee();

	@Before
	public void init() {
		loginobj.setEmail("a@gmail.com");
		loginobj.setPassword("password");

		emp.setEmpId(1);
		emp.setEmpDesignation("developer");
		emp.setEmpEmail("a@gmail.com");
		emp.setEmpFirstName("Aparna");
		emp.setEmpLastName("Singhal");
		emp.setEmpPassword("password");
		emp.setEmpBuhEmail("b@gmail.com");
		emp.setEmpHrEmail("h@gmail.com");
		emp.setEmpPmEmail("p@gmail.com");
	}

	@Test
	public void testloginvalidate() {
		Mockito.when(logindaoimplement.validateDao(loginobj)).thenReturn(emp);
		Assert.assertEquals(loginservice.validateService(loginobj), emp);
	}
}
