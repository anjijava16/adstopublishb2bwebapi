package com.sapta.b2bweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.commons.PersistenceUnitNames;
import com.sapta.b2bweb.commons.SessionManager;
import com.sapta.b2bweb.commons.TransactionManager;
import com.sapta.b2bweb.domainobject.VendorUserDO;

public class VendorUserDAO {
	
	private EntityManager em = null;
	
	public  VendorUserDO retriveVendor(VendorUserDO vendorUserDO){
		try {
			
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				System.out.println("persist");
				em.persist(vendorUserDO);
				TransactionManager.commitTransaction();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorUserDO;
	}
	
	public  VendorUserDO vendorUpdate(VendorUserDO vendorUserDO){
		try {
			
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.merge(vendorUserDO);
				TransactionManager.commitTransaction();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorUserDO;
	}
	
	public  String vendorDelete(VendorUserDO vendorUserDO){
		try {
			
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.remove(vendorUserDO);
				TransactionManager.commitTransaction();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return "";
	}
	
	
	@SuppressWarnings("unchecked")
	public  List<VendorUserDO> retriveUserByEmailOrMobile(String email, String phone){
		List<VendorUserDO> vendorList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorUserDO.FIND_BY_EMAIL_OR_MOBILE);
				q.setParameter(CommonConstants.EMAIL, email);
				q.setParameter(CommonConstants.MOBILE, phone);
				vendorList = q.getResultList();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ee  "+e);
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorList;
	}
	
	@SuppressWarnings("unchecked")
	public  List<VendorUserDO> vendorLogin(String user, String password){
		List<VendorUserDO> vendorList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorUserDO.VENDOR_LOGIN);
				q.setParameter(CommonConstants.MOBILE, user);
				q.setParameter(CommonConstants.EMAIL, user);
				q.setParameter(CommonConstants.PASSWORD, password);
				vendorList = q.getResultList();
			}
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorList;
	}
	
	
	@SuppressWarnings("unchecked")
	public  List<VendorUserDO> retriveByID(Long vendorID){
		List<VendorUserDO> vendorList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(VendorUserDO.RETRIEVE_BY_ID);
				q.setParameter(CommonConstants.VENDORID, vendorID);
				vendorList =  q.getResultList();
			}
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return vendorList;
	}
	
}