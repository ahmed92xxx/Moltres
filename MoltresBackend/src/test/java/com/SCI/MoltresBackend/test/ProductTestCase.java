package com.SCI.MoltresBackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.SCI.MoltresBackend.dao.ProductDAO;
import com.SCI.MoltresBackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {

		context = new AnnotationConfigApplicationContext();
		context.scan("com.SCI.MoltresBackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("ProductDAO");
	}
/*
	@Test
	public void testCrudProduct() {
		// add operation
		product = new Product();
		product.setName("Opoo S53");
		product.setBrand("Opoo");
		product.setDescription("Opoo mobile phone");
		product.setUnitPrice(250000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);

		assertEquals("Something wrong while inserting a product", true, productDAO.add(product));

		// read and update operation
		product = productDAO.get(2);
		product.setName("SAMSUNG GALAXY S7");
		assertEquals("Something wrong while updating a product", true, productDAO.update(product));
		// delete operation
		assertEquals("Something wrong while deleting a product", true, productDAO.delete(product));
		// list opeartion
		assertEquals("Something wrong while fetching a product", 6, productDAO.list().size());
	}
*/
	
	/*
	@Test 
	public void testListOfActiveProduct() {
	assertEquals("Something wrong while fetching a product", 5, productDAO.listActiveProducts().size());
	}
	*/
	/*
	@Test
	public void testListOfActiveProductByCategory() {
		assertEquals("Something wrong while fetching a product",2, productDAO.listActiveProductsByCategory(1).size());
		assertEquals("Something wrong while fetching a product", 3, productDAO.listActiveProductsByCategory(3).size());
	}
	*/
	@Test
	public void testLatestActiveProduct()
	{
		assertEquals("Something wrong while fetching a product",3, productDAO.getLatestActiveProducts(3).size());

	}
	
}
