package com.xwrokz.ibank.customerdemo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/*
 * refer persistence context example method at the end
 * 
 */
public class CustomerDAOImpl implements CustomerDAO {

	private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();

	public void addCustomer(CustomerEntity customer) {
		Transaction transaction = null;
		Session session = null;
		try {
			//customer entity object that we pass as a parameter is in transient state
			session = FACTORY.openSession();
			transaction = session.beginTransaction();
			//this save method stores an object in persistence context
			//once the transaction is committed it will be stored in table
			//here the state of entity object is persistence state
			Integer id = (Integer) session.save(customer);
			//all methods of session can push entity objects to persistence
			//context ex. get, load, update, persist etc
			//to check whether an object is present in persistence context
			//we can use
			boolean b = session.contains(customer);
			
			System.out.println("data saved with id: " + id);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("exception occured" + e.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/*
	 * Method to get the CustomerEntity from DB
	 * we can use get() or load() method [difference written in notes]
	 * get and load method always expects identifier values using primary key
	 * Object get(Entity.class, Serializable idVal): return null if customer not found in table
	 * here id is primary key
	 */
	public CustomerEntity getCustomer(int id) {
		Session session = FACTORY.openSession();
		CustomerEntity customer = session.get(CustomerEntity.class, id);
		// for getting another user the get method will not be executing the query
		// again. It will use the same session and first-level cache comes into picture
		// to retrieve the same data

//		 CustomerEntity customer1 = session.get(CustomerEntity.class, id);
//		 System.out.println(customer1);

		session.close();

		// if I try to access the same CustomerEntity after closing the session
		// this will not use the same session, here I need to have second-level cache
//		Session session1 = FACTORY.openSession();
//		CustomerEntity customer2 = session1.get(CustomerEntity.class, id);
//		System.out.println(customer2);
//		session1.close();

		return customer;
	}
	
	
	/*
	 *  Syntactically, the get() and load() are similar. However, 
	 * if the primary key value, passed as a parameter to the method is not 
	 * present in the database, then get() returns null whereas load() throws 
	 * ObjectNotFoundException.
	 * 
	 * Object load(Entity.class, Serializable idVal)
	 * 
	 * it returns the proxy instance of Entity class with primary key
	 * Load doesn't hit the db, rather it initialize the entity object by lazy 
	 * loading this enhance the performance 
	 * 
	 * get() method returns the data from DB. While load() method just returns the reference 
	 * of an object. Only when we access properties of the object it just loads the data 
	 * from database. It is reason why the load() method can return reference of object 
	 * that might not actually exists.
	 
	 */
	@Override
	public CustomerEntity loadCustomer(int id) {
		Session session = FACTORY.openSession();
		//you are trying to load/get Customer object where id=20. 
		//But assume record is not available in DB.
		CustomerEntity customer = session.load(CustomerEntity.class, id); //step 1
		System.out.println(customer.getCustomerId());//step 2
		System.out.println(customer.getCustomerName());//step 3
		
		//at step 1 hibernate won't fire an query to get customer record from DB,
		//But it actually creates a proxy object (dummy object). This contains nothing
		//other than id = 20 and step 2 will print 20, but in step 3 we are fetching 
		//the customer information from DB. 
		//but this time hibernate fires SQL query. as customer doen't exist, 
		//so it will give ObjectNotFoundException
		
		
		//The get() method fetches data as soon as it’s executed while the load() 
		//method returns a proxy object and fetches only data when object properties 
		//is required. load supports lazy loading. Increased performance
		//We should use the load() method only when we know data exists because it 
		//throws exception when data is not found. In case we want to make sure data 
		//exists we should use the get() method.
		session.close();
		return customer;
	}

	//update(): void
	public void updateCustomer(Integer id, String name) {
		Session session = FACTORY.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		CustomerEntity customer = session.get(CustomerEntity.class, id);
		customer.setCustomerName(name);
		session.update(customer);
		//The changes are committed by an active transaction using the commit() method. 
		transaction.commit();
		System.out.println("updated *********");
		session.close();
	}

	public void saveOrUpdateCustomer(Integer id, String name) {
		Session session = FACTORY.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		CustomerEntity customer = session.get(CustomerEntity.class, id);
		customer.setCustomerName(name);
		//either save is called or update is called
		//data persisted if ID doesn't exists/data updated if ID exists
		//hibernate does it internally
		session.saveOrUpdate(customer);
		transaction.commit();
		System.out.println("updated *********");
		session.close();
	}
	
	//delete(): void
	public void deleteCustomer(Integer id) {
		Session session = FACTORY.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		CustomerEntity customer = session.get(CustomerEntity.class, id);
		session.delete(customer);
		transaction.commit();
		System.out.println("deleted *********");
		session.close();

	}

	@Override
	public List<CustomerEntity> readAllCustomers() {
		Session session = FACTORY.openSession();
		session.beginTransaction();
		List list = session.createQuery("FROM CustomerEntity").list();
		for(Iterator iterator = list.iterator(); iterator.hasNext();) {
			CustomerEntity customer = (CustomerEntity) iterator.next();
			System.out.println(customer);
		}
		session.close();
		return list;
	}
	
	public void persistenceContext() {
		Session session = FACTORY.openSession(); // start of persistence context
		CustomerEntity customerEntity1 = session.get(CustomerEntity.class, 1);
		CustomerEntity customerEntity2 = session.get(CustomerEntity.class, 1);

		//customer1 and customer2 are referring to the same object as Hibernate 
		//Framework confirms that against the same id, there will be only one 
		//object exist in the Persistent context.
		if (customerEntity1 == customerEntity2) {
			System.out.println("references are pointing to the same object");
		}
		session.close();// end of persistence context
		
		
		
		Session session1 = FACTORY.openSession(); // start of another persistence context
		CustomerEntity customerEntity3 = session1.get(CustomerEntity.class, 1);
		
		//the objects customer1 and customer3 are referring to two different 
		//objects as the persistent context is different.
		if (customerEntity1 == customerEntity3) {
			System.out.println("references are pointing to the same object");
		}

		session1.close(); // end of another persistence context
	}

}
