package com.gtu14.persistence;


import java.sql.Date;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.bean.EntityModel.entityRole;
import com.gtu14.entity.Applicant;
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
public class BankDAO { 
	@PersistenceContext
	private EntityManager em;
	
	public void sendRequest(long cardNumber, String accountNumber, long id_request){
		try{
			Request request = em.find(Request.class, id_request);
			System.out.println("HECHO FIND");
			request.setAccountnumber(accountNumber);
			request.setCardnumber(cardNumber);
			request.setState("De banco a estampadora");
			System.out.println("ANTES DEL MERGE");
			em.merge(request);
			System.out.println("DESPUES DEL MERGE");
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
	public void backRequest(long id_request, String comment){
		try{
			Request request = em.find(Request.class, id_request);
			request.setComment(comment);
			request.setState("Cancelada en el banco");
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
	public Request putRequest(long id_request, String comment){
		try{
			Request request = em.find(Request.class, id_request);
			return request;
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
	public void validateRequest(long id_request){
		try{
			Request request = em.find(Request.class, id_request);
			request.setState("Entregada desde el banco a la universidad");
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
	public Bank findBank (String cif_bank){
		try{
			Bank b = em.find(Bank.class, cif_bank);
			return b;
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
	public Request fillRequest(){
		try{
			Date d = new Date(0);
			
			Stamping s = new Stamping("5a", "Estampadora Manolo", "a@manolo.es",
					"Carretera v" , 711);
			em.persist(s);
			
			Bank b = new Bank("4a", "Santander", "a@santander.es", "Plaza c",
					888, s);
			em.persist(b);
			
			University u = new University("3a", "UPM", "a@upm.es",
					"Avenida b", 955, b);
			em.persist(u);
			
			Applicant a = new Applicant("2a", "Jorge", "Ulloa",
					1, d , "Español", "a@mail.com",
					"Calle a", "Madrid", "Madrid", "Estudiante",
					695, u);
			em.persist(a);
			
			Request request= new Request ((long)2, a, u.getBank(),
					a.getUniversity(), b.getStamping(), d,
					"Prueba", "", (long)0, "");
			em.persist(request);
			return request;
			
			
		}catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
		
		
	}
}
