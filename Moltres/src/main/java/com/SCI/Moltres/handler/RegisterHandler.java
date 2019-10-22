package com.SCI.Moltres.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import com.SCI.Moltres.model.RegisterModel;
import com.SCI.MoltresBackend.dao.UserDAO;
import com.SCI.MoltresBackend.dto.Address;
import com.SCI.MoltresBackend.dto.Cart;
import com.SCI.MoltresBackend.dto.User;

@Component
public class RegisterHandler {

	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	
	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		//check password
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))) {
			error.addMessage(new MessageBuilder().error().source("confirm password").defaultText("password do not confirm").build());
			transitionValue ="failure";
			
		}
		
		//check email
		
		if(userDAO.getByEmail(user.getEmail())!=null) {
			error.addMessage(new MessageBuilder().error().source("email").defaultText("email is already use").build());
			transitionValue ="failure";
		}
		
		
		return transitionValue;

	}
	
	public String saveAll(RegisterModel model) {
		String transitionValue = "success";
		
		User user = model.getUser();
		if(user.getRole().equals("USER")) {
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		userDAO.add(user);
		
		Address billing = model.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
	
}
