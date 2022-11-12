package com.xwrokz.ibank.customerdemo;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

	public static void main(String[] args) {

//		Scanner scanner = new Scanner(System.in);
//		CustomerEntity customer = new CustomerEntity();
//		
//		System.out.println("Enter Customer Name");
//		String name = scanner.next();
//		customer.setCustomerName(name);
//		
//		System.out.println("Enter Customer Id");
//		int id = scanner.nextInt();
//		customer.setCustomerId(id);

//		new CustomerDAOImpl().addCustomer(new CustomerEntity(47, "Ramya"));

//		CustomerEntity customer = new CustomerDAOImpl().getCustomer(23);
//		System.out.println(customer);

//		 new CustomerDAOImpl().loadCustomer(23);

//		 new CustomerDAOImpl().updateCustomer(33, "adarsh");

//		 new CustomerDAOImpl().deleteCustomer(33);

//		 new CustomerDAOImpl().readAllCustomers();
//		 new CustomerDAOImpl().persistenceContext();

		// with queries
//		new CustomerDAOImplWithQuerying().readAllCustomers();

//		new CustomerDAOImplWithQuerying().getCustomersWithAgeAboveFiftyUsingQuery();

//		new CustomerDAOImplWithQuerying().getCustomersWithAgeAboveFiftyUsingHQL();

//		new CustomerDAOImplWithQuerying().getCustomersNamesAndAgeWithAgeAboveFiftyHQL();

//		new CustomerDAOImplWithQuerying().sqlInjectionDemo("1", "1=1");

//		new CustomerDAOImplWithQuerying().postionParameter("akash", 45);

//		new CustomerDAOImplWithQuerying().namedParameter("akash", 45);
		
		
//		new CustomerDAOImplWithOthrs().aggregateFunctions();

//		new CustomerDAOImplWithOthrs().groupBy();
		
		new CustomerDAOImplWithOthrs().having();
		
	}
}
