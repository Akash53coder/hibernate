package com.xworkz.caching;

public class AppRunner {

	public static void main(String[] args) {
		
//		new CustomerDAOImpl().firstLevelCache(1);
		
		new CustomerDAOImpl().secondLevelCache(1);
		
	}
	
}

