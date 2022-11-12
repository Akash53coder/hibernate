package com.xworkz.caching;

public interface CustomerDAO {

	public void firstLevelCache(int id);
	
	public void secondLevelCache(int id);
}
