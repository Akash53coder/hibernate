package com.xwrokz.ibank.customerdemo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CustomerDAOImplWithQuerying {

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();

	/*
	 * use of native SQL query to get the list of Customer
	 * For this I need to create instance of SQLQuery by using createSQLQuery() of session API
	 * and using list() method of SQLQuery I can get the list of Customers
	 */
	public void readAllCustomers() {
		Session session = FACTORY.openSession();
		String sql = "select * FROM Customer_Details";
		List list = session.createSQLQuery(sql).list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CustomerEntity customer =  (CustomerEntity)iterator.next();
			System.out.println(customer);
		}
		session.close();
	}
	
	/*
	 * method to return customer with age > 50
	 */
	public void getCustomersWithAgeAboveFiftyUsingQuery() {
		Session session = FACTORY.openSession();
		String sql = "select d.CUSTOMERID, d.CUSTOMERNAME from Customer_Details d where d.CUSTOMERAGE>50";
		List list = session.createSQLQuery(sql).list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CustomerEntity customer =  (CustomerEntity)iterator.next();
			System.out.println(customer);
		}
		session.close();
	}
	
	/*
	 * Running Native SQL queries from Hibernate ignores the purpose of ORM. 
	 * so we use HQL : A query with object oriented paradigm
	 * deals with entity object, not with DB table or column 
	 * createQuery() of session API, returns the Object of Query and list() is used
	 * on this object to retrieve the list of data
	 */
	public void getCustomersWithAgeAboveFiftyUsingHQL() {
		Session session = FACTORY.openSession();
		String sql = "FROM CustomerEntity c where c.age > 50";
		List list = session.createQuery(sql).list();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CustomerEntity customer =  (CustomerEntity)iterator.next();
			System.out.println(customer);
		}
		session.close();
	}
	
	public void getCustomersNamesAndAgeWithAgeAboveFiftyHQL() {
		Session session = FACTORY.openSession();
		String sql = "SELECT c.customerName, c.age  FROM CustomerEntity c where c.age > 50";
		List list = session.createQuery(sql).list();
		System.out.println(list);
		session.close();
	}
	
	/*
	 * SQL injection attack:  here additional "or" condition, causes where clause to 
	 *  always evaluate to TRUE. Which fetches all the data from DB.
	 *  attackers can get these data 
	 *  parameters passed here are name = 1, anotherName = 1=1
	 *  
	 *  One of the root cause for the SQL Injection attack is dynamic query creation by 
	 *  concatenating a constant base query string and a user input string.
	 *  Hibernate provides a solution to SQL injection attacks by allowing the user to 
	 *  construct the queries dynamically at runtime using parameters binding technique. 
	 *  If required, the compiled query can be reused with different parameters, 
	 *  which enhance the performance. Hibernate supports two ways for parameter binding.
	 *  1. positioned parameter 2. named parameter.
	 *  
	 */
	public void sqlInjectionDemo(String name, String anotherName) {
		Session session = FACTORY.openSession();
//		String p = "'" + (1 == 1) + "'";
		List list = session.createQuery("from CustomerEntity where customerName = "
				 + name + " or " + anotherName).list();
		System.out.println(list);
		session.close();
	}
	
	
	/*
	 * Positioned parameter
	 * Query API is used to provide 3 methods
	 * Query setParameter (int position, Object Value)
	   Query setXXX (int position, Object Value)
	   Query setProperties (Object object)
	 */
	public void postionParameter(String name, int age) {
		Session session = FACTORY.openSession();
		//use of just ? is not supported for the current version
		Query query = session.createQuery("from CustomerEntity c where c.customerName = ?0");
//		query.setString(0, name);//deprecated since 5.2
		query.setParameter(0, name);
		
		session.close();
		
		Session session1 = FACTORY.openSession();
		Query query1 = session1.createQuery("from CustomerEntity c where c.customerName = ?0 and "
				+ "c.age = ?1");
		query1.setParameter(0, name);
		query1.setParameter(1, age);
		System.out.println(query1.list());
		session1.close();
	}
	
	/*
	 * Named Parameters
	 * Query API provides these methods
	 * Query setParameter (String name, Object Value)
	   Query setXXX (String name, Object Value)
       Query setProperties (Object object)
     * It is allowed to define a positioned parameter before a named parameter.
     * Defining a positioned parameter after named parameter will cause the error.
	 */
	public void namedParameter(String name, int age) {
		Session session = FACTORY.openSession();
		//use of just ? is not supported for the current version
		Query query = session.createQuery("from CustomerEntity c where c.customerName = :name and c.age=:age");
//		query.setString("name", name);//deprecated since 5.2
		query.setParameter("name", name);
		query.setParameter("age", age);
		System.out.println(query.list());
		session.close();
	}
	
}
