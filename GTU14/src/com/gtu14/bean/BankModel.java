package com.gtu14.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
 * Modelo para el manejo de peticiones y cambios
 * sobre una solicitud en el banco
 * 
 * @author Jorge Ulloa Núñez
 *
 */
@Named
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
	private String cifStamping;
	
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public String getCifStamping() {
		return cifStamping;
	}
	public void setCifStamping(String cifStamping) {
		this.cifStamping = cifStamping;
	}
	/**
	 * Da de alta una entidad universidad, banco o estampadora con datos del .xhtml
	 */
	public String submitRequest(){
		
		bankDAO.sendRequest(request.getCardnumber(), request.getAccountnumber(), 
				request.getId_request(), getCifStamping());
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
		List<Request> lr = requestDAO.getRequest(b);
		List<Request> lrFinal = new ArrayList<Request>() ;
		for (Request request : lr){
			if((request.getState().equals(Request.state.BANCO_IDA))||(request.getState().equals(Request.state.BANCO_VUELTA))){
				lrFinal.add(request);
			}
		}
		return lrFinal;
		
	}
		
	
	public String putRequest(){
		if(request.getState().equals(Request.state.BANCO_IDA)){
			setCifStamping(request.getBank().getStamping().getCif_stamping());
			return "formularioBancoIda";
		} else{
			return "formularioBancoVuelta";
		}
	}
	
	
}
