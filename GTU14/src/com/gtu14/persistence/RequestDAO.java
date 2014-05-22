package com.gtu14.persistence;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.gtu14.entity.Applicant;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Request;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;

@Stateful
public class RequestDAO {
	@PersistenceContext
	private EntityManager em;
	@EJB
	private EntityDAO entityDao;
	
	/**
	 * Busca las solicitudes prendientes de una entidad origen.
	 * 
	 * @param 	fromEntity	Entidad de origen  (banco, estampadora o universidad).
	 * @return	requestList	Lista de solicitudes pendientes o null si no hay.
	 */
	public List<Request> getRequest(Object fromEntity){
		if(fromEntity instanceof University){
			return this.getRequest((University) fromEntity);
		}else if(fromEntity instanceof Bank){
			return this.getRequest((Bank) fromEntity);
		}
		else if(fromEntity instanceof Stamping){
			return this.getRequest((Stamping) fromEntity);
		}
		return null;	//TODO Informar de que el parámetro no es una entidad.
	}
	/**
	 * Busca las solicitudes prendientes de una universidad origen.
	 * 
	 * @param 	fromUniversity	Universidad de origen.
	 * @return	requestList	Lista de solicitudes pendientes o null si no hay.
	 */
	public List<Request> getRequest(University fromUniversity){
		String fromUniversityCif = fromUniversity.getCif_university();
		//Vemos si existe la universidad
		if(entityDao.findUniversity(fromUniversityCif) == null)
			return null;
		TypedQuery<Request> queryRequestByUnivesity = em.createQuery(
			    "SELECT req FROM Request req WHERE req.university.cif_university = :cif", Request.class)
			    .setParameter("cif", fromUniversityCif);
		List<Request> requestList;
		try{
			requestList = queryRequestByUnivesity.getResultList();
		}catch(Exception ex){
			return null;
		}
		return (List<Request>) requestList;
	}
	/**
	 * Busca las solicitudes prendientes de una estampadora origen.
	 * 
	 * @param 	fromStamping	Estampadora de origen.
	 * @return	requestList		Lista de solicitudes pendientes o null si no hay.
	 */
	public List<Request> getRequest(Stamping fromStamping){
		String fromStampingCif = fromStamping.getCif_stamping();
		//Vemos si existe la universidad
		if(entityDao.findStamping(fromStampingCif) == null)
			return null;
		TypedQuery<Request> queryRequestByStamping = em.createQuery(
			    "SELECT req FROM Request req WHERE req.stamping.cif_stamping = :cif", Request.class)
			    .setParameter("cif", fromStampingCif);
		List<Request> requestList;
		try{
			requestList = queryRequestByStamping.getResultList();
		}catch(Exception ex){
			return null;
		}
		return (List<Request>) requestList;
	}
	/**
	 * Busca las solicitudes prendientes de un banco origen.
	 * 
	 * @param 	fromBank	Banco de origen.
	 * @return	requestList	Lista de solicitudes pendientes o null si no hay.
	 */
	public List<Request> getRequest(Bank fromBank){
		String fromBankCif = fromBank.getCif_bank();
		//Vemos si existe la universidad
		if(entityDao.findBank(fromBankCif) == null)
			return null;
		TypedQuery<Request> queryRequestByBank = em.createQuery(
			    "SELECT req FROM Request req WHERE req.bank.cif_bank = :cif", Request.class)
			    .setParameter("cif", fromBankCif);
		List<Request> requestList;
		try{
			requestList = queryRequestByBank.getResultList();
			
		     
		}catch(Exception ex){
			return null;
		}
		return (List<Request>) requestList;
	}
		
	/**
	 * Crea una solicitud genérica en la base de datos.
	 * 
	 * @return	newRequest		Solicitud creada o null si ya existía antes.
	 */
	public Request newRequest(long requestId, 
								String accountnumber,
								Applicant applicant,
								Bank bank,
								University university,
								Stamping stamping,
								String comment, 
								Date requestDate, 
								Request.state state, 
								long cardNumber){
		//Vemos si existe la solicitud
		if(findRequest(requestId) != null)
			return null;
		
		Request newRequest = new Request(requestId, applicant, bank, university, stamping, requestDate, state, comment, cardNumber, accountnumber);
		try{
			em.persist(newRequest);
		}catch(Exception ex){
			return null;
		}
		return newRequest;
	}
	/**
	 * Crea una solicitud genérica en la base de datos.
	 * 
	 * @return	newRequest		Solicitud creada o null si ya existía antes.
	 */
	public Request newRequest(Request newRequest){
		//Vemos si existe la solicitud
		if(findRequest(newRequest.getId_request()) != null)
			return null;
		
		try{
			em.persist(newRequest);
		}catch(Exception ex){
			return null;
		}
		return newRequest;
	}
	
	
	/**
	 * Busca una solicitud en la base de datos.
	 * 
	 * @param 	requestId		Id de la solicitud.
	 * @return	resultRequest	Solucitud encontrada o null.
	 */
	private Request findRequest(long requestId){
		try{
			Request resultRequest = em.find(Request.class, requestId);
			return resultRequest;
		}catch(Exception ex){
			return null;
		}
		
	}
}
