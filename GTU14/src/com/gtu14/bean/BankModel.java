package com.gtu14.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Request;
import com.gtu14.persistence.BankDAO;
import com.gtu14.persistence.RequestDAO;


/**
 * Modelo para el manejo de propiedades relacionadas con
 * universidad, banco o estampadora (entidades jurídicas).
 * 
 * @author Jorge Ulloa Núñez
 *
 */
@Named
@RequestScoped
public class BankModel implements Serializable{ //Entidad = Universidad || Banco || Estampadora
	
	private static final long serialVersionUID = -2633611667469166418L;
	
	public enum applicantRole {ALUMNO, DOCENTE, PAS, INVESTIGADOR};
	
	@EJB
	private BankDAO bankDAO;
	@EJB
	private RequestDAO requestDAO;
	@Inject
	private Request request;
		
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	/**
	 * Da de alta una entidad universidad, banco o estampadora con datos del .xhtml
	 */
	public String submitRequest(){
		System.out.println("ENTRO AL MODEL");
		System.out.println("ID = "+ request.getId_request());
		bankDAO.sendRequest(request.getCardnumber(), request.getAccountnumber(), 
				request.getId_request());
		System.out.println("VUELVO AL MODEL");
		return "tablaSolicitudes";
	}
	
	public String testingBank(){
		Request r = bankDAO.fillRequest();
		request.setApplicant(r.getApplicant());
		request.setUniversity(r.getUniversity());
		request.setBank(r.getBank());
		request.setId_request(r.getId_request());
		return ("formularioBancoIda");
	}
	
	public String cancelRequest(){
		bankDAO.backRequest(request.getId_request(), request.getComment());
		return "tablaSolicitudes";
	}
	
	public String validateRequest(){
		bankDAO.validateRequest(request.getId_request());
		return "tablaSolicitudes";
	}
	
	public List<Request> getrequestList(){
		
		return requestDAO.getRequest(request.getId_request());
	}
	
	public String putRequest(){
		Request r = bankDAO.putRequest(request.getId_request(), request.getState());
		if(r.getState().equals("De banco a estampadora")){
			request.setApplicant(r.getApplicant());
			request.setUniversity(r.getUniversity());
			request.setBank(r.getBank());
			request.setId_request(r.getId_request());
			return "formularioBancoIda";
		}
		else{
			request.setApplicant(r.getApplicant());
			request.setUniversity(r.getUniversity());
			request.setBank(r.getBank());
			request.setId_request(r.getId_request());
			return "formularioBancoVuelta";
		}
	}
	
}
