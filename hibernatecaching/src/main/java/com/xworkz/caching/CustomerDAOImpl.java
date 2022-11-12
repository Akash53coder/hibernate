package com.xworkz.caching;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.query.Query;

public class CustomerDAOImpl implements CustomerDAO {

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();

	/*
	 * Database caching is nothing but a data is stored in a buffered memory.
	 * for the data which we demand again n again to reduce the number of DB calls.
	 * 
	 * Hibernate provides first level cache with session objects.
	 * it uses memory rather than hitting DB.
	 * for the first time after hitting the DB, it caches the results in cache memory
	 * in both first-level and second-level cache
	 * as session1 and session2 are two different sessions
	 * after closing session1, session2 fired a query to get the customer
	 * if we didn't close session1 then hibernate won't fire any query 
	 * rather it uses a same result from cached data for id 1.
	 * 
	 * first-level cache is enabled by default, we cannot disable that 
	 * the entity object will remain in the cache until session is open 
	 * once the session is closed, associated cache is cleared.
	 * if my session object is long lived, fetching large amount of data will leads
	 * to out of memory error
	 * 
	 * second - level cache
	 * default it is disabled, developer needs to enable it
	 * SessionFactory is responsible for second-level cache
	 * This cache data is available for the entire application
	 * once the session factory is closed then cached data is removed
	 * 
	 * Use session.evict(entityObject) to remove cached data.
	 * 
	 * (session/session-factory) are objects in a heap area. That means the cache is stored in 
	 * the RAM only. And because of that, it gives faster access to data rather than databases.
	 * 
	 */
	public void firstLevelCache(int id) {
		Session session = FACTORY.openSession();
		CustomerEntity customer1 = session.get(CustomerEntity.class, id);
		System.out.println(customer1);
		session.close();
		
		Session session2 = FACTORY.openSession();
		CustomerEntity customer2 = session2.get(CustomerEntity.class, id);
		System.out.println(customer2);
		session2.close();
	}

	/*
	 * Hibernate does not provide any default implementation for the second-level cache. 
	 * It gives CacheProvider interface, so any third party Cache which implements CacheProvider 
	 * interface can be hooked as Second level cache, like EHCache or NCache.
	 * 
	 * for this we need to set a property "use_second_level_cache" in configuration file
	 * and need to EHcahe dependency with hibernate-ehcache dependency
	 * and also use @Cacheable. and @Cache(usage=CacheConcurrencyStrategy.READ_ONLY) on entity class
	 * 
	 */
	public void secondLevelCache(int id) {
		Session session = FACTORY.openSession();
		CustomerEntity customer1 = session.get(CustomerEntity.class, id);
		System.out.println(customer1);
		
		session.evict(customer1);
		
		session.close();
		
		Session session2 = FACTORY.openSession();
		CustomerEntity customer2 = session2.get(CustomerEntity.class, id);
		System.out.println(customer2);
		session2.close();
	}
	
	 /*
	  * by default cache is always works with get and load method
	  * in order to make it work with query set property "use_cache_query" in config file.
	  * use query.setCacheable(true);
	  */
	public void secondLevelCacheWithQuery(int id) {

		Session session = FACTORY.openSession();
		Query query =  session.createQuery("from CustomerEntity c where c,customerId =:id");
		CustomerEntity customer1 = (CustomerEntity) query.uniqueResult();
		query.setCacheable(true);
		System.out.println(customer1);
		
		session.close();
		
		Session session1 = FACTORY.openSession();
		Query query1 = session1.createQuery("from CustomerEntity c where c,customerId =:id");
		query1.setCacheable(true);
		CustomerEntity customer2 = (CustomerEntity)	query1.uniqueResult();
		System.out.println(customer2);
		
		session1.close();
	
	}

}
