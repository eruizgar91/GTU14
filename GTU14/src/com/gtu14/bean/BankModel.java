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
	public void submitRequest(){
		bankDAO.sendRequest(request.getBank().getName(), request.getBank().getCif_bank(),
				request.getCardnumber(), request.getAccountnumber(), request.getBank().getStamping(),
				request.getApplicant().getCif_applicant());
	}
	/**
	 * Da de baja una entidad universidad, banco o estampadora con dado el cif en el .xhtml
	 */
	
	
	
}
