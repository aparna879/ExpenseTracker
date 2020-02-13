package com.ojasa.reimburseit.servicetest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ojasa.reimburseit.dao.ExpenseRequestDetailDaoImplement;
import com.ojasa.reimburseit.model.ExpenseRequestDetail;
import com.ojasa.reimburseit.service.GetExpenseRequestDetailService;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class GetExpenseRequestDetailServiceTest {

	@InjectMocks
	GetExpenseRequestDetailService getexpenserequestdetailservice;

	@Mock
	ExpenseRequestDetailDaoImplement expenserequestdetaildaoimplement;

	ExpenseRequestDetail expreqdet1 = new ExpenseRequestDetail();
	ExpenseRequestDetail expreqdet2 = new ExpenseRequestDetail();

	List<ExpenseRequestDetail> list = new ArrayList();

	@Before
	public void init() {

		Date date1 = new Date(0);
		expreqdet1.setExpensecost(10000);
		expreqdet1.setExpensedate(date1);
		expreqdet1.setExpensename("Hotel");

		Date date2 = new Date(0);
		expreqdet2.setExpensecost(1000);
		expreqdet2.setExpensedate(date2);
		expreqdet2.setExpensename("Dinner");

		list.add(expreqdet1);
		list.add(expreqdet2);
	}

	@Test
	public void getAllExpenseRequestDetailTest() {
		Mockito.when(expenserequestdetaildaoimplement.getExpenseRequestDetail(1)).thenReturn(list);
		Assert.assertEquals(getexpenserequestdetailservice.getAllExpenseRequestDetail(1), list);
	}
}
