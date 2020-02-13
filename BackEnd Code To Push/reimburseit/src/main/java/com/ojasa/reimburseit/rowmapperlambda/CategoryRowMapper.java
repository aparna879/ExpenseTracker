package com.ojasa.reimburseit.rowmapperlambda;

import org.springframework.jdbc.core.RowMapper;

import com.ojasa.reimburseit.model.Category;

public class CategoryRowMapper {

	public CategoryRowMapper() {
	}

	public static final RowMapper<Category> categoryRowMapperLambda = (rs, rownum) -> {
		Category category = new Category();
		category.setName(rs.getString("category_name"));
		return category;
	};
}

