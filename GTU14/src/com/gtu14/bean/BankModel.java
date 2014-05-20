package com.gtu14.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Request;
import com.gtu14.entity.Bank;
import com.gtu14.entity.User;
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
@ManagedBean
@RequestScoped
public class BankModel implements Serializable{ 
	
	private static final long serialVersionUID = -2633611667469166418L;
	
	@EJB
	private BankDAO bankDAO;
	@EJB
	private RequestDAO requestDAO;
	@Inject
	Request request;
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
		
		bankDAO.sendRequest(request.getCardnumber(), request.getAccountnumber(), 
				request.getId_request());
		return "Banco";
	}
	
	/*public String testingBank(){
		Request r = bankDAO.fillRequest();
		request.setApplicant(r.getApplicant());
		request.setUniversity(r.getUniversity());
		request.setBank(r.getBank());
		request.setId_request(r.getId_request());
		return "formularioBancoIda";
	}*/
	
	public String cancelRequest(){
		bankDAO.backRequest(request.getId_request(), request.getComment());
		return "Banco";
	}
	
	public String validateRequest(){
		bankDAO.validateRequest(request.getId_request());
		return "Banco";
	}
	
	public List<Request> getrequestList(){
		User u=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		Bank b = bankDAO.findBank(u.getCif());
		return requestDAO.getRequest(b);
		
	}
		
	
	public String putRequest(){
		
		return "formularioBancoIda";
	}
	
	
}
