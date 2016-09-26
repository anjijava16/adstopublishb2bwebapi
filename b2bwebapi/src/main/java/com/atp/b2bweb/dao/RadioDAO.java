package com.atp.b2bweb.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.atp.b2bweb.common.PersistenceUnitNames;
import com.atp.b2bweb.common.SessionManager;
import com.atp.b2bweb.common.TransactionManager;
import com.atp.b2bweb.domainobject.RadioDO;

public class RadioDAO {
private EntityManager em = null;
	
	public  RadioDO addRadio(RadioDO radioDo){
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				TransactionManager.beginTransaction(em);
				em.persist(radioDo);
				TransactionManager.commitTransaction();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return radioDo;
	}
	
	@SuppressWarnings("unchecked")
	public List<RadioDO> retrive(){
		List<RadioDO> radioList = null;
		try {
			em = SessionManager.createManager(PersistenceUnitNames.PERSISTENCE_UNIT_NAME);
			if(em != null){
				Query q = em.createNamedQuery(RadioDO.FIND_ALL);
				radioList =  q.getResultList();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			SessionManager.closeEntityManager(em);
		}
		return radioList;
	}
}
