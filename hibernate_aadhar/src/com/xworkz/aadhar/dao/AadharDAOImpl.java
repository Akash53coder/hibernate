package com.xworkz.aadhar.dao;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.xworkz.signle_sessionfactory.*;

import com.xworkz.aadhar.entity.AadharEntity;

public class AadharDAOImpl implements AadharDAO {

	@Override
	public void saveAadharEntity() {
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction transaction = null;

		try {
			// from jar for configuration object and register annotated class refer 
			//sessionfactory_provider_hib_adr project
			sessionFactory = SessionFactoryProvider.getSessionFactory();

			session = sessionFactory.openSession();

			transaction = session.beginTransaction();

			AadharEntity aadhar = new AadharEntity("adarsh", true);
			session.save(aadhar);
			// session.persist(aadhar);

			transaction.commit();

		} catch (HibernateException exp) {
			transaction.rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				System.out.println("session closed");
				session.close();
			}
		}

	}

	@Override
	public void readAadharEntity() {

		Transaction transaction = null;
		Session session = null;
		SessionFactory sessionFactory = null;
		try {
			sessionFactory = SessionFactoryProvider.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			AadharEntity aadhar = session.get(AadharEntity.class, 1432);
			// or session.load(...);
			System.out.println(aadhar);

		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed");
			}
		}

	}

//
	@Override
	public void updateAadharEntity() {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = SessionFactoryProvider.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			AadharEntity aadhar = new AadharEntity(1212, "zoya", true);
			session.update(aadhar);
			transaction.commit();
		} catch (HibernateException exp) {
			transaction.rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed");
			}

		}

	}

//
	@Override
	public void deleteAadharEntity() {
		Session session = null;
		SessionFactory sessionFactory = null;
		Transaction transaction = null;
		try {
			sessionFactory = SessionFactoryProvider.getSessionFactory();
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();

			AadharEntity aadhar = new AadharEntity(1212, "zoya", true);
			session.delete(aadhar);
			transaction.commit();
			//closing sessionFactory singleton after performing all operations
			SessionFactoryProvider.closeSessionFactory();
		} catch (HibernateException exp) {
			transaction.rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
				System.out.println("session closed");
			}
		}
	}

}
