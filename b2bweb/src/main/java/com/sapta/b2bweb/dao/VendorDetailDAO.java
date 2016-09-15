package com.sapta.b2bweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.commons.PersistenceUnitNames;
import com.sapta.b2bweb.commons.SessionManager;
import com.sapta.b2bweb.commons.TransactionManager;
import com.sapta.b2bweb.domainobject.VendorDetailDO;

public class VendorDetailDAO {

private EntityManager em = null;
	
	public  VendorDetailDO addVendorDetails(VendorDetailDO vendorDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.persist(vendorDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorDetailDO;
	}
	
	public  VendorDetailDO updateVendorDetails(VendorDetailDO vendorDetailDO){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.merge(vendorDetailDO);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorDetailDO;
	}
	
	@SuppressWarnings("unchecked")
	public List<VendorDetailDO> retriveByVendorID(Long vendorid){
		List<VendorDetailDO> vendorDetailList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorDetailDO.FIND_BY_VENDOR_ID);
				q.setParameter(CommonConstants.VENDORID, vendorid);
				vendorDetailList = q.getResultList();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorDetailList;
	}
	
}
