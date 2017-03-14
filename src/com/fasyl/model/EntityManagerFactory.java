package com.fasyl.model;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class EntityManagerFactory {
	private static final long serialVersionUID = 1L;
	@Inject
	private Resource resource;
	public EntityManager getEM(){
		return resource.getEntityManager();
	}
}
