package com.sapta.b2bweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.commons.PersistenceUnitNames;
import com.sapta.b2bweb.commons.SessionManager;
import com.sapta.b2bweb.commons.TransactionManager;
import com.sapta.b2bweb.domainobject.VendorBusinessDetailDO;

public class VendorBusinessDetailsDAO {
	
	private EntityManager em = null;
	
	public  VendorBusinessDetailDO addBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.persist(vendorBusinessDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBusinessDetailDO;
	}
	
	public  VendorBusinessDetailDO updateBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.merge(vendorBusinessDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBusinessDetailDO;
	}
	
	@SuppressWarnings("unchecked")
	public List<VendorBusinessDetailDO> retriveByVendorID(Long vendorid){
		List<VendorBusinessDetailDO> vendorBusinessDetailList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorBusinessDetailDO.FIND_BY_VENDOR_ID);
				q.setParameter(CommonConstants.VENDORID, vendorid);
				vendorBusinessDetailList =  q.getResultList();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorBusinessDetailList;
	}
	
}