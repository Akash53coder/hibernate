package com.xwrokz.ibank.customerdemo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerDAOImplJoins {

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();
	
	/*
	 * retrieve data by linking between two associated entities using joins.
	 * there are 2 types in joins 1. Implicit and 2.Explicit 
	 * 
	 * Implicit: here we don't use joins keyword.
	 * 			 association is de-referenced using '.'
	 */
	public void implicitJoins() {
		Session session = FACTORY.openSession();
		 session.createQuery("select c.customerId, c.Address.addressId from CustomerEntity c where "
				+ "c.Address is not null").list();
		session.close();
	}
	
	public void explicitJoins() {
		Session session = FACTORY.openSession();
		session.createQuery("select c.customerId, a.addressId from CustomerEntity c Inner join "
				+ "c.Address a").list();
		session.close();
	}
}
