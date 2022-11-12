package com.xworkz.loading;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class LoadingDAOImpl implements LoadingDAO{

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();
	
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

}
