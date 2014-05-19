package com.gtu14.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Request;
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


	public String getRequest(){
		
		//System.out.println();
		r=s.getRequest(1);
		return "formularioEstampadora";
		
	}
	

}
