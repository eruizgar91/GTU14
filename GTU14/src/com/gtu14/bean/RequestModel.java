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
import com.gtu14.persistence.RequestDAO;
import com.gtu14.persistence.ApplicantDAO;

@Named
@RequestScoped
public class RequestModel {
	@EJB
	private RequestDAO requestDAO;
	@EJB
	private ApplicantDAO applicantDAO;
	@Inject
	private Request request;
	@Inject
	private Applicant applicant;
	
	private static long request_id = 0;

	private UIOutput createRequestMsg;
	private UIOutput deleteRequestMsg;
	
	public UIOutput getDeleteRequestMsg() {
		return deleteRequestMsg;
	}
	public void setDeleteRequestMsg(UIOutput deleteRequestMsg) {
		this.deleteRequestMsg = deleteRequestMsg;
	}
	public UIOutput getCreateRequestMsg() {
		return createRequestMsg;
	}
	public void setCreateRequestMsg(UIOutput createRequestMsg) {
		this.createRequestMsg = createRequestMsg;
	}
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
			
		Applicant newApplicant = applicantDAO.newApplicant(applicant);
		
		request_id =+ request_id;
	
		Request newRequest = requestDAO.newRequest(request_id, 
													vacio , 
													newApplicant, 
													(Bank) null , 
													applicant.getUniversity(), 
													(Stamping) null , 												
													vacio ,
													(java.sql.Date) new Date(), 
													Request.state.UNIVERSIDAD_IDA,
													(long) 0 );
		
		if(newApplicant == null){
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[ERROR] El aplicante ya existe.");
		}else if(newRequest == null){
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[ERROR] La solicitud ya existe.");
		}else{
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[OK] La solicitud ha sido creada.");
		}
		
		return null;
	}

}