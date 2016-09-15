package com.sapta.b2bweb.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.commons.PersistenceUnitNames;
import com.sapta.b2bweb.commons.SessionManager;
import com.sapta.b2bweb.commons.TransactionManager;
import com.sapta.b2bweb.domainobject.VendorBankDetailDO;

public class VendorBankDetailsDAO {
	
	private EntityManager em = null;
	
	public  VendorBankDetailDO addBankDetails(VendorBankDetailDO vendorBankDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.persist(vendorBankDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBankDetailDO;
	}
	
	public  VendorBankDetailDO updateBankDetails(VendorBankDetailDO vendorBankDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.merge(vendorBankDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBankDetailDO;
	}
	
	public VendorBankDetailDO retriveByVendorID(Long vendorid){
		VendorBankDetailDO vendorBankDetailList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorBankDetailDO.FIND_BY_VENDOR_ID);
				q.setParameter(CommonConstants.VENDORID, vendorid);
				vendorBankDetailList = (VendorBankDetailDO) q.getResultList();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBankDetailList;
	}
	
}