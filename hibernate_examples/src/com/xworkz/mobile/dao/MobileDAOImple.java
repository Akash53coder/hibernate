package com.xworkz.mobile.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.xworkz.mobile.entity.MobileEntity;

public class MobileDAOImple implements MobileDAO {

	@Override
	public void saveMobileEnity() {
		Transaction transaction = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			// bootstrap hibernate framework
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(MobileEntity.class);
			// data from the xml is parsed into java objects or (compiled), checks the
			// definition and syntactical errors
			// by default the connection manager is DriverManager internally

			// if name of the configuartion file is starting with hibernate
			// (hibernate.cfg.xml) then
			// hibernate.cfg.xml is optional in configuaration.configure() method
			configuration.configure("hibernate.cfg.xml");
			// or//
			// configuration.configure();

			// creating sessionFactory
			// buildSessionFactory internally creates a sessionFactory object
			sessionFactory = configuration.buildSessionFactory();

			// to perform db operation, this is a short lived object
			// one start startsup
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			MobileEntity mobileEntity = new MobileEntity(4, "one plus", 39999.0, "4GB", "Black");
			// save mothod will save the object
			session.save(mobileEntity);

			// set auto commit is false by default in hibernate
			// commit and rollbacks are used for DML statements like update, delete, insert
			// but reading doesnot require commits and rollbacks and even transaction
			transaction.commit();

		} catch (HibernateException hibernateException) {
			// commit rolled back
			transaction.rollback();
			System.out.println("tansaction rolled back ");
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			} else {
				System.out.println("session is not closed");
			}
			if (sessionFactory != null) {
				sessionFactory.close();
				System.out.println("session factory is closed");
			} else {
				System.out.println("session factory is not closed");
			}
		}

	}

	@Override
	public void readMobileEntity() {
		SessionFactory sessionFactory = null;
		Session session = null;
		try {
			Configuration config = new Configuration();
			config.configure();
			config.addAnnotatedClass(MobileEntity.class);
			sessionFactory = config.buildSessionFactory();
			session = sessionFactory.openSession();
			// internally uses resultset
			// takes 2 parameters one is Entity class , Id primary key to read perticular
			// record
			MobileEntity mobileEntity = session.get(MobileEntity.class, 1);
			System.out.println("Data From DB " + mobileEntity);

		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		}
	}

	@Override
	public void updateMobileEntity() {
		Transaction transaction = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			// bootstrap hibernate framework
			Configuration configuration = new Configuration();

			configuration.addAnnotatedClass(MobileEntity.class);
			// data from the xml is parsed into java objects or (compiled), checks the
			// definition and syntactical errors
			// by default the connection manager is DriverManager internally

			// if name of the configuartion file is starting with hibernate
			// (hibernate.cfg.xml) then
			// hibernate.cfg.xml is optional in configuaration.configure() method
			configuration.configure("hibernate.cfg.xml");
			// or//
			// configuration.configure();

			// creating sessionFactory
			// buildSessionFactory internally creates a sessionFactory object
			sessionFactory = configuration.buildSessionFactory();

			// to perform db operation, this is a short lived object
			// one start startsup
			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			MobileEntity mobileEntity = new MobileEntity(1, "blackberry", 39999.0, "4GB", "Black");
			// save mothod will save the object
			session.update(mobileEntity);

			// set auto commit is false by default in hibernate
			// commit and rollbacks are used for DML statements like update, delete, insert
			// but reading doesnot require commits and rollbacks and even transaction
			transaction.commit();

		} catch (HibernateException hibernateException) {
			// commit rolled back
			transaction.rollback();
			System.out.println("tansaction rolled back ");
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session is closed");
			} else {
				System.out.println("session is not closed");
			}
			if (sessionFactory != null) {
				sessionFactory.close();
				System.out.println("session factory is closed");
			} else {
				System.out.println("session factory is not closed");
			}
		}

	}

	@Override
	public void deleteMobileEntity() {
		Transaction transaction = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			configuration.addAnnotatedClass(MobileEntity.class);
			sessionFactory = configuration.buildSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			MobileEntity mobileEntity = session.get(MobileEntity.class, 4);
			session.delete(mobileEntity);
			transaction.commit();

		} catch (HibernateException exp) {
			transaction.rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session is not closed");
			}
			if (sessionFactory != null) {
				sessionFactory.close();
			} else {
				System.out.println("session Factory is not closed");
			}

		}
	}

}
