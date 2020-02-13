package com.ojasa.reimburseit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ojasa.reimburseit.service.CategoryReturnService;

@RestController
@RequestMapping("/category")
public class CategoryReturnController {

	@Autowired
	CategoryReturnService categoryreturnservice;

	@GetMapping("/sendcategory")
	public List<String> sendcategory() {
		return categoryreturnservice.sendcategory();
	}
}
