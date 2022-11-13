package com.xworkz.loading;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoadingDAOImpl implements LoadingDAO{

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();
	
	//code to demonstrate OneToMany mapping without fetching techniques
	@Override
	public void getDetails() {
		Session session = FACTORY.openSession();
		Query query = session.createQuery("from UserEntity");
		List<UserEntity> list = query.list();
		for(Iterator<UserEntity> itr = list.iterator(); itr.hasNext();) {
			UserEntity user = itr.next();
			List<LaptopEntity> laptops = user.getLaptops();
			System.out.println(user.getUserid()+ " -- " + user.getUsername() + " -- " + laptops);
		}
		session.close();
	}

	/*
	 * Fetch techniques: 
	 * Eager and lazy loadings are design patterns.
	 * Observe the query hibernate fires, with lazy loading it won't get the laptop details 
	 * with user details in a single shot
	 * 
	 */
	@Override
	public void getDetails(int id) {

		Session session = FACTORY.openSession();
		UserEntity user = session.get(UserEntity.class, id);
		System.out.println(user);
		session.close();
	
	}

}
