package com.gtu14.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
public class UniversityModel implements Serializable{ //Entidad = Universidad || Banco || Estampadora
	
	private static final long serialVersionUID = -2633611667469166418L;
<<<<<<< HEAD
		
=======
	
>>>>>>> f794dfbc49e98b40be1d37071a9992792ebc52ef
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
	 *
	 */
	public String submitRequest(){
		universityDAO.sendRequest(request.getId_request());
<<<<<<< HEAD
		return "universidad";
	}
	public String cancelRequest(){
		universityDAO.backRequest(request.getId_request(), request.getComment());
		return "universidad";
=======
		return "Universidad";
	}
	public String cancelRequest(){
		universityDAO.backRequest(request.getId_request(), request.getComment());
		return "Universidad";
>>>>>>> f794dfbc49e98b40be1d37071a9992792ebc52ef
	}
	
	public String validateRequest(){
		universityDAO.validateRequest(request.getId_request());
<<<<<<< HEAD
		return "universidad";
=======
		return "Universidad";
>>>>>>> f794dfbc49e98b40be1d37071a9992792ebc52ef
	}
	
	public List<Request> getrequestList(){
		User u=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		University uni = universityDAO.findUniversity(u.getCif());
<<<<<<< HEAD
		return requestDAO.getRequest(uni);
=======
		List<Request> lr = requestDAO.getRequest(uni);
		List<Request> lrFinal = new ArrayList<Request>() ;
		for (Request request : lr){
			if((request.getState().equals(Request.state.UNIVERSIDAD_IDA))||(request.getState().equals(Request.state.UNIVERSIDAD_VUELTA))){
				lrFinal.add(request);
			}
		}
		return lrFinal; 
>>>>>>> f794dfbc49e98b40be1d37071a9992792ebc52ef
		
	}
		
	
	public String putRequest(){
<<<<<<< HEAD
		
		return "formularioUniversidadIda";
=======
		if(request.getState().equals(Request.state.UNIVERSIDAD_IDA)){
			return "formularioUniversidadIda";
		} else{
			return "formularioUniversidadVuelta";
		}
>>>>>>> f794dfbc49e98b40be1d37071a9992792ebc52ef
	}
	
	
}
