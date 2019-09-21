package com.SCI.MoltresBackend.dao;

import java.util.List;

import com.SCI.MoltresBackend.dto.Category;

public interface CategoryDAO {

	List<Category> list();
	Category get(int id);
	
}
