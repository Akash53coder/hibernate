package com.xworkz.mobile;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.xworkz.mobile.dao.MobileDAOImple;
import com.xworkz.mobile.entity.MobileEntity;

public class MobileTester {

	public static void main(String[] args) {
		
		MobileDAOImple mobileDAOImple = new MobileDAOImple();
		//mobileDAOImple.saveMobileEnity();
		
		mobileDAOImple.readMobileEntity();
	}
	
}
