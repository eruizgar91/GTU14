package com.gtu14.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
//import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;


import com.gtu14.entity.Request;
import com.gtu14.persistence.UniversityDAO;


/**
 * Modelo para el manejo de propiedades relacionadas con
 * universidad, banco o estampadora (entidades jurídicas).
 * 
 * @author Enrique Ruiz García
 *
 */
@Named
@RequestScoped
public class UniversityModel implements Serializable{ //Entidad = Universidad || Banco || Estampadora
	
	private static final long serialVersionUID = -2633611667469166418L;
		
	@EJB
	private UniversityDAO universityDAO;
	@EJB
	private RequestDAO requestDAO;
	@Inject
	private Request request;
	@Inject
	private User user;
		
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
		universityDAO.sendRequest(request.getId_request());
		return "universidad";
	}
	public String cancelRequest(){
		universityDAO.backRequest(request.getId_request(), request.getComment());
		return "universidad";
	}
	
	public String validateRequest(){
		universityDAO.validateRequest(request.getId_request());
		return "universidad";
	}
	
	public List<Request> getrequestList(){
		User u=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		University uni = universityDAO.findUniversity(u.getCif());
		return requestDAO.getRequest(uni);
		
	}
		
	
	public String putRequest(){
		
		return "formularioUniversidadIda";
	}
	
	
}
