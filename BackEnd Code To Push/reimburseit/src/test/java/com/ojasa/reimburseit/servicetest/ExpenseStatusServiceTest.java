package com.ojasa.reimburseit.servicetest;

import java.sql.Date; 

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ojasa.reimburseit.dao.ExpenseStatusDaoImplement;
import com.ojasa.reimburseit.model.EmailParameters;
import com.ojasa.reimburseit.service.ExpenseStatusService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseStatusServiceTest {

	@InjectMocks
	ExpenseStatusService expensestatusservice;

	@Mock
	ExpenseStatusDaoImplement expensestatusdaoimplement;

	EmailParameters emailparam = new EmailParameters();

	@Before
	public void init() {

		Date date = new Date(0);
		emailparam.setApproveremail("abhigyan.nayak89@gmail.com");
		emailparam.setCurrentdate(date);
		emailparam.setRequestername("Aparna");
		emailparam.setRequesterrole("Human Resource Manager");
		emailparam.setEmpid(1);
		emailparam.setExpensecategory("Team Outing");
		emailparam.setTotalcost(1000);
		emailparam.setEmpname("Omkar");
	}

	@Test
	public void updateacceptstatustest() throws Exception {
		Mockito.when(expensestatusdaoimplement.updateacceptstatus(1, "aparna879.official@gmail.com", "Approved"))
				.thenReturn(emailparam);

		Assert.assertEquals(expensestatusservice.updateacceptstatus(1, "aparna879.official@gmail.com", "Approved"),
				"Accepted");
	}

	@Test
	public void updaterejectstatustest() {
		Mockito.when(expensestatusdaoimplement.updaterejectstatus(1, "aparna879.official@gmail.com", "Rejected"))
				.thenReturn("Rejected");

		Assert.assertEquals(expensestatusservice.updaterejectstatus(1, "aparna879.official@gmail.com", "Rejected"),
				"Rejected");
	}

	@Test
	public void ftacceptservicetest() {
		Mockito.when(expensestatusdaoimplement.ftacceptdao(1)).thenReturn("Accepted");
		Assert.assertEquals(expensestatusservice.ftacceptservice(1), "Accepted");
	}

	@Test
	public void ftrejectservicetest() {
		Mockito.when(expensestatusdaoimplement.ftrejectdao(1)).thenReturn("Rejected");
		Assert.assertEquals(expensestatusservice.ftrejectservice(1), "Rejected");
	}
}

