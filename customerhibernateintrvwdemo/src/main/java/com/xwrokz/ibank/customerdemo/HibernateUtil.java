package com.xwrokz.ibank.customerdemo;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 * making this class a singleton object
 * so adding a private constructor, so no other class can access and create an instance of 
 * this class
 * all variables are made static and final
 * 
 */
public class HibernateUtil {

	private static final SessionFactory SESSIONFACTORY;
	private static final StandardServiceRegistry REGISTRY;

	private HibernateUtil() {
	}

	static {
		try {
			REGISTRY = new StandardServiceRegistryBuilder().configure(
					"hibernate.cfg.xml")
					.build();

			Metadata metadata = new MetadataSources(REGISTRY).getMetadataBuilder().build();

			SESSIONFACTORY = metadata.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	public static SessionFactory getSessionFactory() {
		return SESSIONFACTORY;
	}
}
