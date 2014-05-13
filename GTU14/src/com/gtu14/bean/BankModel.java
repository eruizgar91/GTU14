package com.gtu14.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Bank;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.entity.Request;
import com.gtu14.entity.User;
import com.gtu14.persistence.BankDAO;


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
		System.out.println("cif = "+ request.getId());
		bankDAO.sendRequest(request.getCardnumber(), request.getAccountnumber(), 
				request.getId_request());
		System.out.println("VUELVO AL MODEL");
		return ("index");
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
		bankDAO.backRequest(request.getApplicant().getCif_applicant(), request.getComment());
		return ("index");
	}
	
	public String validateRequest(){
		bankDAO.validateRequest(request.getApplicant().getCif_applicant());
		return ("index");
	}
	
}
