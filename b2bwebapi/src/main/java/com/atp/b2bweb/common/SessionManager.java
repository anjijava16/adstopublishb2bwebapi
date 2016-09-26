package com.atp.b2bweb.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author administrator
 * 
 */
public class SessionManager {

	private static EntityManagerFactory factory;

	public static EntityManager createManager(String persistentUnitName) {
		factory = Persistence.createEntityManagerFactory(persistentUnitName);
	    EntityManager em = factory.createEntityManager();
		return em;
	}

	public static void closeEntityManager(EntityManager entityManager) {
		if (entityManager != null && entityManager.isOpen()){
			entityManager.close();
			// Mark it to null so that garbage collector pick is soon
			entityManager = null;
		}			
	}
	
}