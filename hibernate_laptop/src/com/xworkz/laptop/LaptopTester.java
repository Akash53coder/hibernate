package com.xworkz.laptop;

import com.xworkz.laptop.dao.LaptopDAOImpl;

public class LaptopTester {

	public static void main(String[] args) {

		LaptopDAOImpl laptop = new LaptopDAOImpl();
		//laptop.getAllLaptops();
		//laptop.getLaptopById();
		//laptop.getBrandById();
		//laptop.getRamByName();
		
		//laptop.createLaptopEntity();
		laptop.updateLaptopEntity();
	}

}
