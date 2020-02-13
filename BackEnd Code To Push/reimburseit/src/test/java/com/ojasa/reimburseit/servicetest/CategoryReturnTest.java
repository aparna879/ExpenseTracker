package com.ojasa.reimburseit.servicetest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ojasa.reimburseit.dao.CategoryReturnDaoImplement;
import com.ojasa.reimburseit.service.CategoryReturnService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryReturnTest {

	@InjectMocks
	CategoryReturnService categoryreturnservice;

	@Mock
	CategoryReturnDaoImplement categoryreturndaoimplement;

	List<String> list = new ArrayList<>();

	@Before
	public void init() {

		list.add("Placement");
		list.add("Team Outing");
		list.add("Business Visit");
	}

	@Test
	public void sendcategorytest() {
		Mockito.when(categoryreturndaoimplement.sendcategory()).thenReturn(list);
		Assert.assertEquals(categoryreturnservice.sendcategory(), list);
	}

}

