package com.gtu14.persistence;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.entity.Request;
import com.gtu14.entity.Stamping;


@Stateful
public class StampingDAO  { 
	@PersistenceContext
	private EntityManager em;
	
		
	public Stamping findStamping (String cif){
		try{
			Stamping s = em.find(Stamping.class, cif);
			return 	s;
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	public Request getRequest (long id_request){
		try{
			Request r= em.find(Request.class, id_request);
			return 	r;
		} catch (Exception ex) {
			throw new EJBException(ex.getMessage());
		}
	}
	 public List<Request> getRequests(String cif_estampadora)  {
	        
	      List<Request> resultList = (List<Request>) em.createQuery("SELECT r FROM Request r WHERE r.stamping.cif_stamping="+"'"+cif_estampadora+"'").getResultList();
	      Request removedReq=null;
	      for (Request request : resultList) {
	    	  if(request.getState()!=Request.state.ESTAMPADORA)
	    		  removedReq = request;
		}
	      if(removedReq!=null){
	      resultList.remove(removedReq);
	      
	      }
		return resultList;
	        
     }
	 
	 public void sendRequest(long id_request){
			try{
				Request request = em.find(Request.class, id_request);
				
				request.setState(Request.state.BANCO_VUELTA);
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
	
}
