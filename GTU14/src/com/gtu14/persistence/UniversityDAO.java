package com.gtu14.persistence;


import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;






//import com.gtu14.bean.EntityModel.entityRole;
//import com.gtu14.entity.Bank;


import com.gtu14.entity.Bank;
//import com.gtu14.entity.University;
import com.gtu14.entity.Request;
import com.gtu14.entity.University;

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
	
	public void sendRequest(long id_request, Bank bank){
		try{
			Request request = em.find(Request.class, id_request);
			request.setState(Request.state.BANCO_IDA);
			request.setBank(bank);
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	public void backRequest(long id_request, String comment){
		try{
			Request request = em.find(Request.class, id_request);
			request.setComment(comment);
			request.setState(Request.state.CANCELADO);
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
			request.setState(Request.state.FINALIZADO);
			em.merge(request);
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		} 
	}
	public University findUniversity (String username){
		try{
			University uni = em.find(University.class, username);
			return uni;
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	
}
