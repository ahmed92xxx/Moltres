package com.SCI.MoltresBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.SCI.MoltresBackend.dao.CategoryDAO;
import com.SCI.MoltresBackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.SCI.MoltresBackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}

	/*
	 * @Test public void testAddCategory() {
	 * 
	 * category = new Category();
	 * 
	 * category.setName("Television");
	 * category.setDescription("Television description");
	 * category.setImageURL("TEL1.png");
	 * assertEquals("succefuly added category inside table !!",true,categoryDAO.add(
	 * category)); }
	 * 
	 */
	/*
	 * @Test public void testGetCategory() {
	 * 
	 * category = categoryDAO.get(1);
	 * assertEquals("succefuly fetched a single category from table !!","Television"
	 * ,category.getName()); }
	 */

	/*
	 * @Test public void testUpdateCategory() {
	 * 
	 * category = categoryDAO.get(1); category.setName("TV");
	 * assertEquals("succefuly updated a single category from table !!",true,
	 * categoryDAO.update(category)); }
	 */
	/*
	 * @Test public void testDeleteCategory() {
	 * 
	 * category = categoryDAO.get(1);
	 * assertEquals("succefuly deleted a single category from table !!", true,
	 * categoryDAO.delete(category)); }
	 */
	/*
	 * @Test public void testListCategory() {
	 * assertEquals("succefuly fetched list of category from table !!", 1,
	 * categoryDAO.list().size()); }
	 */

	@Test
	public void TestCRUDCategory() {

		// add operation
		category = new Category();
		category.setName("Laptop");
		category.setDescription("Laptop description");
		category.setImageURL("Lap1.png");
		assertEquals("succefuly added category inside table !!", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Mobile");
		category.setDescription("Mobile description");
		category.setImageURL("MOB1.png");
		assertEquals("succefuly added category inside table !!", true, categoryDAO.add(category));

		// update operation
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("succefuly updated a single category from table !!", true, categoryDAO.update(category));

		//delete operation
		assertEquals("succefuly deleted a single category from table !!", true,categoryDAO.delete(category));
		
		//list operation
		assertEquals("succefuly fetched list of category from table !!", 2,categoryDAO.list().size());
	}

}
