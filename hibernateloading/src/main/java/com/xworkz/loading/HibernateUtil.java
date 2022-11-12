package com.xworkz.loading;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/*
 * making this class a singleton object
 * so adding a private constructor, so no other class can access and create an instance. 
 * all variables are made static and final
 * creating a SessionFactory with hibernate v.5 is totally different from v.3 & v.4.
 * org.hibernate.boot.registry.StandardServiceRegistry: The StandardServiceRegistry class loads 
   all the hibernate configurations and mappings from the hibernate.cfg.xml.
   
 * org.hibernate.boot.MetadataSources: Use MetadataSources API to create an instance of org.hibernate.boot.Metadata.
 * org.hibernate.boot.Metadata: Use the instance of MetaData and create the instance of SessionFactory by calling 
   getSessionFactoryBuilder().build() method of v.5.
 */
public class HibernateUtil {

	private static final SessionFactory SESSIONFACTORY;
	private static final StandardServiceRegistry REGISTRY;

	private HibernateUtil() {}

	static {
		try {
			REGISTRY = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

			Metadata metadata = new MetadataSources(REGISTRY).getMetadataBuilder().build();

			SESSIONFACTORY = metadata.getSessionFactoryBuilder().build();
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e.getMessage());
		}

	}

	public static SessionFactory getSessionFactory() {
		return SESSIONFACTORY;
	}
}
