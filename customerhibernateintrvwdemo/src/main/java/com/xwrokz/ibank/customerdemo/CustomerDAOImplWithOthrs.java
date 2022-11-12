package com.xwrokz.ibank.customerdemo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CustomerDAOImplWithOthrs {

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();
	
	/*
	 * Aggregate functions help to compute results using the attribute values available 
	 * in the database.
	 * avg(), max(), min(), sum(), count(), count(*)
	 * Imp. The result should be typecasted to the proper data type.
	 */
	public void aggregateFunctions() {
		Session session = FACTORY.openSession();
		List list = session.createQuery("select max(c.age) from CustomerEntity c").list();
		System.out.println(list.get(0));
	}
	
	public void groupBy() {
		Session session = FACTORY.openSession();
		Query query = session.createQuery("select city, count(*) from CustomerEntity group by city");
		List<Object[]> list = query.list();
		for(Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {
			System.out.println(Arrays.toString(itr.next()));
		}
		session.close();
	}
	
	public void having() {
		Session session = FACTORY.openSession();
		Query query = session.createQuery("select city, count(*) from CustomerEntity "
				+ "group by city having count(*)>2");
		List<Object[]> list = query.list();
		for(Iterator<Object[]> itr = list.iterator(); itr.hasNext();) {
			System.out.println(Arrays.toString(itr.next()));
		}
		session.close();
		
		//similarly use it for Like clause, desc (descending), 
		//SQL function that manipulate the string are
		
//		FUNCTIONS	                    |        DEFINITION
//   ___________________________________|_____________________________________________		
// 		length(stringValue)	            |    to get number of characters
//		upper(stringValue)	            |    string to upper-case
//		lower(stringValue)	            |    string to lower-case
//		concat(stringVal1,stringVal2)   |    Concatenates two given strings
//		substring(stringVal1,position)  |    substrings from the given position to the last index
//		substring(stringVal1,pos1,pos2) |    substrings from pos1 to pos2
		
	}
}
