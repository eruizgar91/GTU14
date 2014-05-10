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
 * @author Jorge Ulloa Núñez
 *
 */
@Stateful
public class BankDAO { //Entidad = Universidad || Banco || Estampadora
	@PersistenceContext
	private EntityManager em;
	
	public void sendRequest(String bankName, String cif_Bank, long cardNumber, String accountNumber, Stamping stamping, String cif_applicant){
		try{
			Request request = em.find(Request.class, cif_applicant);
			request.setAccountnumber(accountNumber);
			request.setCardnumber(cardNumber);
			request.setStamping(stamping);
			request.setState("De banco a estampadora");
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
}
