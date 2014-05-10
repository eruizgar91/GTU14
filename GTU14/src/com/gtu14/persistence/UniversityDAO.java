package com.gtu14.persistence;


import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.bean.EntityModel.entityRole;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.entity.Request;

/**
 * Objeto de acceso a datos (DAO) que maneja las entidades
 * (jurídicas): Universidad, banco y estampadora.
 * 
 * @author Enrique Ruiz García
 *
 */
@Stateful
public class UniversityDAO { 
	@PersistenceContext
	private EntityManager em;
	
	public void sendRequest(String universityName, String cif_University, Bank bank, String cif_applicant){
		try{
			Request request = em.find(Request.class, cif_applicant);
			request.setBank(bank);
			request.setState("De universidad a banco");
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
}
