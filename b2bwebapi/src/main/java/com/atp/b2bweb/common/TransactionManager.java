package com.atp.b2bweb.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TransactionManager {

	@SuppressWarnings("rawtypes")
	private static final ThreadLocal threadLocal = new ThreadLocal();
	private static EntityTransaction transaction;

	@SuppressWarnings("unchecked")
	public static void beginTransaction(EntityManager entityMngr) {
		transaction = (EntityTransaction) threadLocal.get();
		if (transaction == null) {
			transaction = entityMngr.getTransaction();
			transaction.begin();
			threadLocal.set(transaction);
		}
	}

	@SuppressWarnings("unchecked")
	public static void commitTransaction() {
		EntityTransaction tx = (EntityTransaction) threadLocal.get();
		if (tx != null && tx.isActive())
			transaction.commit();
		threadLocal.set(null);
	}

	@SuppressWarnings("unchecked")
	public static void rollbackTransaction() {
		EntityTransaction tx = (EntityTransaction) threadLocal.get();
		threadLocal.set(null);
		if (tx != null && tx.isActive())
			tx.rollback();
	}

}
