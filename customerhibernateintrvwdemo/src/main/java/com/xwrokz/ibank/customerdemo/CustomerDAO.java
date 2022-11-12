package com.xwrokz.ibank.customerdemo;

import java.util.List;

public interface CustomerDAO {

	public void addCustomer(CustomerEntity customer);

	public CustomerEntity getCustomer(int id);
	
	public CustomerEntity loadCustomer(int id);

	public void updateCustomer(Integer id, String name);

	public void deleteCustomer(Integer id);
	
	public void saveOrUpdateCustomer(Integer id, String name);
	
	public List<CustomerEntity> readAllCustomers();

}
