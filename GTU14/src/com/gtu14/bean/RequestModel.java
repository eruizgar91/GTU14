package com.gtu14.bean;



import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.Applicant;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Request;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.persistence.RequestDAO;
import com.gtu14.persistence.ApplicantDAO;
import com.gtu14.persistence.EntityDAO;

@Named
@RequestScoped
public class RequestModel {
	@EJB
	private RequestDAO requestDAO;
	@EJB
	private ApplicantDAO applicantDAO;
	@EJB
	private EntityDAO entityDAO;
	@Inject
	private Request request;
	@Inject
	private Applicant applicant;
	
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Applicant getApplicant() {
		return applicant;
	}
	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
	
	public String submitRequest(){
						
		String vacio = "vacio";
						
		Applicant newApplicant = applicantDAO.newApplicant(applicant.getCif_applicant(), 
															applicant.getAddress(), 
															new java.sql.Date(applicant.getBorndate().getTime()), 
															applicant.getEmail(), 
															applicant.getFirstname(), 
															applicant.getLastname(), 
															applicant.getNacionality(), 
															applicant.getPopulation(), 
															applicant.getProvince(), 
															applicant.getRole(), 
															applicant.getTypecif(), 
															applicant.getTelephone(), 
															applicant.getUniversity());		
				
		long request_id = Long.parseLong(applicant.getCif_applicant().substring(0, applicant.getCif_applicant().length()-1), 10);		
				
		Request newRequest = requestDAO.newRequest(request_id,
													vacio , 
													newApplicant, 
													(Bank) null , 
													applicant.getUniversity(), 
													(Stamping) null , 												
													vacio ,
													new java.sql.Date(new Date().getTime()), 
													Request.state.UNIVERSIDAD_IDA,
													(long) 0 );
				
		return null;
	}

}