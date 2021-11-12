package com.xworkz.laptop.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.xworkz.laptop.entity.LaptopEntity;
import com.xworkz.single_sessionfactory.SessionFactoryProvider;

public class LaptopDAOImpl implements LaptopDAO {

	@Override
	public void getAllLaptops() {
		Session session = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			String hqlQuery = " FROM LaptopEntity";
			Query query = session.createQuery(hqlQuery);

			List listOfLaptopEntity = query.list();

			System.out.println(listOfLaptopEntity);

		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session not closed");
			}
		}

	}

	@Override
	public void getLaptopById() {
		Session session = null;
		Transaction transaction = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			String hqlQuery = "FROM LaptopEntity where id=1";
			Query query = session.createQuery(hqlQuery);
			Object object = query.uniqueResult();
			LaptopEntity laptop = (LaptopEntity) object;
			System.out.println(laptop);

		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session not closed");
			}
		}
	}

	@Override
	public void getBrandById() {

		Session session = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			String hqlQuery = "SELECT brand FROM LaptopEntity WHERE id=2 ";
			// brand is instance variable name, not the column name
			Query query = session.createQuery(hqlQuery);
			String brand = (String) query.uniqueResult();
			// uniqueResult always returns Object type
			System.out.println(brand);
		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session is not closed");
			}
		}

	}

	@Override
	public void getRamByName() {

		Session session = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			String hqlQuery = "SELECT laptop.ram FROM LaptopEntity as laptop WHERE name='macbookair'";
			// brand is instance variable name, not the column name
			Query query = session.createQuery(hqlQuery);
			int ram = (int) query.uniqueResult();
			System.out.println(ram);
		} catch (HibernateException exp) {
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session is not closed");
			}
		}

	}

//	@Override
//	public void createLaptopEntity() {
//		Session session=null;
//		Transaction transaction=null;
//		try {
//			session = SessionFactoryProvider.getSessionFactory().openSession();
//			transaction = session.beginTransaction();
//			String hqlQuery = "INSERT INTO LaptopEntity (name,brand,price,ram)"
//					+ "SELECT name, brand, price, ram FROM LaptopEntity";
//			Query query = session.createQuery(hqlQuery);
//			int valueInserted = query.executeUpdate();
//			System.out.println(valueInserted);
//			SessionFactoryProvider.closeSessionFactory();
//		}catch(HibernateException exp) {
//			transaction.rollback();
//			System.out.println(exp.getMessage());
//		} finally {
//			if (session != null) {
//				session.close();
//			} else {
//				System.out.println("session is not closed");
//			}
//		}
//	}

	@Override
	public void updateLaptopEntity() {
		Session session = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			String hqlQuery = "UPDATE LaptopEntity SET price=58000 WHERE id=4";
			Query query = session.createQuery(hqlQuery);
			// different approach to transaction
			session.beginTransaction();
			int noOfRowsAffected = query.executeUpdate();
			System.out.println(noOfRowsAffected);
			session.getTransaction().commit();
			SessionFactoryProvider.closeSessionFactory();

		} catch (HibernateException exp) {
			session.getTransaction().rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session is not closed");
			}
		}

	}

	@Override
	public void deleteLaptopEntity() {

		Session session = null;
		try {
			session = SessionFactoryProvider.getSessionFactory().openSession();
			session.beginTransaction();
			String hqlQuery = "DELETE FROM LaptopEntity WHERE id=4";
			Query query = session.createQuery(hqlQuery);
			int noOfRowsAffected = query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException exp) {
			session.getTransaction().rollback();
			System.out.println(exp.getMessage());
		} finally {
			if (session != null) {
				session.close();
			} else {
				System.out.println("session is not closed");
			}
		}
	}

}
