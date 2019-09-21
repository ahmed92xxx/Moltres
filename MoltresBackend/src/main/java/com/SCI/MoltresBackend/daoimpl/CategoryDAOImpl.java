package com.SCI.MoltresBackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.SCI.MoltresBackend.dao.CategoryDAO;
import com.SCI.MoltresBackend.dto.Category;
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	// Only for test
	//
	//
	private static List<Category> categories = new ArrayList<>();
	static {

		Category category = new Category();

		// first category
		category.setId(1);
		category.setName("Television");
		category.setDescription("Television description");
		category.setImageURL("TEL1.png");

		categories.add(category);

		// Second category
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Mobile description");
		category.setImageURL("MOL1.png");

		categories.add(category);

		// third category
		category = new Category();
		category.setId(3);
		category.setName("PC");
		category.setDescription("PC description");
		category.setImageURL("PC1.png");

		categories.add(category);
	}
	

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Category get(int id) {
		for(Category category : categories) {
			if(category.getId()==id) return category;
		}
		return null;
	}

}
