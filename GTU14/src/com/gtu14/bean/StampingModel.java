package com.gtu14.bean;

import java.io.Serializable;
import java.util.List;










import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Request;
import com.gtu14.entity.User;
import com.gtu14.persistence.StampingDAO;

@Named
@RequestScoped
public class StampingModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String comment;
	@EJB
	StampingDAO s;
	
	@Inject
	Request request;	
	
	

	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public StampingModel() {
	}


	public StampingDAO getS() {
		return s;
	}


	public void setS(StampingDAO s) {
		this.s = s;
	}




	public Request getRequest() {
		return request;
	}


	public void setRequest(Request request) {
		this.request = request;
	}
	public List<Request> getrequestList(){
		
		User user=(User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
		if(user!=null){
		return s.getRequests(user.getCif());
		
		}
		return null;
		
	}

	protected Request request() {
        Request request;
        request = (Request) FacesContext.getCurrentInstance().getExternalContext()
           .getRequestMap().get("request");

        return (request);
    }

	public String findRequest(){
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("request",request);
		return "formularioEstampadora";
		
	}
	public String sendRequest(){
		request=(Request)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("request");
		s.sendRequest(request.getId_request());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("request");
		return "Estampadora";
	}
	
	public String cancelRequest(){
		request=(Request)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("request");
		s.backRequest(request.getId_request(), comment);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("request");
		return "Estampadora";
	}
	

}
