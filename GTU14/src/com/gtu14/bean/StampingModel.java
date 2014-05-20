package com.gtu14.bean;

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
public class StampingModel {
	@EJB
	StampingDAO s;
	
	@Inject
	Request r;	
	
	

	public StampingModel() {
	}


	public StampingDAO getS() {
		return s;
	}


	public void setS(StampingDAO s) {
		this.s = s;
	}




	public Request getR() {
		return r;
	}


	public void setR(Request r) {
		this.r = r;
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

	public String getRequest(){
		
		return "formularioEstampadora";
		
	}
	

}
