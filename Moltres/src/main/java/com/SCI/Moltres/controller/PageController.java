package com.SCI.Moltres.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SCI.Moltres.exception.ProductNotFoundException;
import com.SCI.MoltresBackend.dao.CategoryDAO;
import com.SCI.MoltresBackend.dao.ProductDAO;
import com.SCI.MoltresBackend.dto.Category;
import com.SCI.MoltresBackend.dto.Product;
@Controller
public class PageController {
		
	private static final Logger logger= LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "home");

		
		logger.info("inside PageController index method - INFO");
		logger.debug("inside PageController index method - DEBUG");

		// passing list category
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickHome", true);

		return mv;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);

		return mv;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);

		return mv;
	}

	/*
	 * Methodes to load all products base on category
	 **/
	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passing list category
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllProducts", true);

		return mv;
	}

	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// fetch single category

		Category category = null;
		category = categoryDAO.get(id);
		mv.addObject("title", category.getName());

		// passing list category
		mv.addObject("categories", categoryDAO.list());
		// passing single category
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", true);

		return mv;
	}
	
	/*
	 * 
	 * Single product view
	 * ****/
	@RequestMapping(value ="/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		product.setViews(product.getViews() +1);
		
		//update view count
		productDAO.update(product);
		
		mv.addObject("title",product.getName());
		mv.addObject("product",product);
		mv.addObject("userClickShowProduct",true);
		
		
		return mv;
		
	}
	
	 /*
	  * 
	  * mapping flow
	  * 
	  **********/
	
	@RequestMapping(value = { "/register" })
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "sign-up");
		return mv;
	}
	
	@RequestMapping(value = { "/login" })
	public ModelAndView login(@RequestParam(name="error",required=false)String error) {
		ModelAndView mv = new ModelAndView("login");
		if(error != null ) {
			mv.addObject("message","invalid username and password");
		}
		mv.addObject("title", "Login");

		return mv;
	}
	//access denied page
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Caught You !!");
		mv.addObject("errorDescription", "You are not authorized to access to this area ! ");
		return mv;
	}

}
