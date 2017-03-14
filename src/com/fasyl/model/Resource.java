package com.fasyl.model;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.EntityManagerFactory;

public class Resource {
	private static EntityManagerFactory factory;
	
	@Produces
	/*public static EntityManager getEntityManager(){
		factory = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager em = factory.createEntityManager();
		return em;
	}*/
	public static EntityManager getEntityManager() {
		if (factory == null) {                       /* only do this ONCE!!! */
            factory = Persistence.createEntityManagerFactory("persistenceUnit");
		}
		EntityManager em = factory.createEntityManager();
		return em;
	}
}
