package com.gtu14.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.User;
import com.gtu14.persistence.RequestDAO;
import com.gtu14.persistence.ApplicantDAO;

@Named
@RequestScoped
public class RequestModel {
	@EJB
	private UserDAO userDAO;
	@EJB
	private ApplicantDAO applicantDAO;
	@Inject
	private Request request;
	@Inject
	private Applicant applicant;

	private UIOutput createUserMsg;
	private UIOutput deleteUserMsg;
	
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
		
		Applicant newApplicant = applicantDAO.newApplicant( applicant.getCif_applicant(),
															applicant.getFirstname(),
															applicant.getLastname(),
															applicant.getTypecif(),
															applicant.getBorndate(),
															applicant.getEmail(),
															applicant.getAddress(),
															applicant.getPopulation(),
															applicant.getProvince(),
															applicant.getRole(),
															applicant.getTelephone(),
															applicant.getUniversity );

		Request newRequest = requestDAO.newRequest( newApplicant,
													null,
													applicant.getUniversity(),
													null,
													'',
													new Date(),
													Request.state.UNIVERSIDAD_IDA,
													null);

		if(newApplicant == null){
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[ERROR] El aplicante ya existe.");
		}else if(){
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[ERROR] La solicitud ya existe.");
		}else{
			createRequestMsg.setRendered(true);
			createRequestMsg.setValue("[OK] La solicitud ha sido creado.");
		}
		
		return null;
	}

}