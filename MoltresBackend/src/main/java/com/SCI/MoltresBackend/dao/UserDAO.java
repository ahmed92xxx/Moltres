package com.SCI.MoltresBackend.dao;

import java.util.List;

import com.SCI.MoltresBackend.dto.Address;
import com.SCI.MoltresBackend.dto.User;



public interface UserDAO {

	// user related operation
	User getByEmail(String email);
	User get(int id);

	boolean add(User user);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	
	
	Address getBillingAddress(int userId);
	
	List<Address> listShippingAddresses(int userId);
	

	
}
