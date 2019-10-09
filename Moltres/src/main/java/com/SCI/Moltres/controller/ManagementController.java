package com.SCI.Moltres.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.SCI.Moltres.utils.FileUploadUtility;
import com.SCI.Moltres.validator.ProductValidator;
import com.SCI.MoltresBackend.dao.CategoryDAO;
import com.SCI.MoltresBackend.dao.ProductDAO;
import com.SCI.MoltresBackend.dto.Category;
import com.SCI.MoltresBackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public ModelAndView showManageProduct(@RequestParam(name="operation",required=false)String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts",true);
		mv.addObject("title","Manage Products");
		
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product",nProduct);
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message","Product Submitted Successfully !");
			}
		}
		return mv;
	}
	
	//return categories for all mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.list();
		
	}
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product")Product mProduct , BindingResult results , Model model , HttpServletRequest request) {
		
		
		new ProductValidator().validate(mProduct, results);
		
		//check error
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts",true);
			model.addAttribute("title","Manage Products");
			model.addAttribute("message","Validation failed for submission");
			return "page";
		}
		
		logger.info(mProduct.toString());
		
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}

}
