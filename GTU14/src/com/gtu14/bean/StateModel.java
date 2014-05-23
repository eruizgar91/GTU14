package com.gtu14.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
//import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Bank;
import com.gtu14.entity.Request;
import com.gtu14.entity.University;
import com.gtu14.entity.User;
import com.gtu14.persistence.RequestDAO;
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
public class StateModel implements Serializable{ //Entidad = Universidad || Banco || Estampadora
	
	private static final long serialVersionUID = -2633611667469166418L;

	
	@EJB
	private RequestDAO requestDAO;
	@Inject
	private Request request;
	@Inject
	private User user;
	
	private UIOutput showState;
		
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	
	
	public String submitRequest(){
		showState.setValue("El estado de su solicitud es:"+(requestDAO.findRequest(request.getId_request()).getState()));
		return "Solicitud";
	} 
	
	public UIOutput getShowState() {
		return showState;
	}
	public void setShowState(UIOutput showState) {
		this.showState = showState;
	}
	
}
