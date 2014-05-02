package com.gtu14.bean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Inject;
import javax.inject.Named;

import com.gtu14.entity.User;
import com.gtu14.persistence.UserDAO;

@Named
@RequestScoped
public class UserModel {
	@EJB
	private UserDAO userDAO;
	@Inject
	private User user;
	private UIOutput createUserMsg;
	private UIOutput deleteUserMsg;
	
	public UIOutput getDeleteUserMsg() {
		return deleteUserMsg;
	}
	public void setDeleteUserMsg(UIOutput deleteUserMsg) {
		this.deleteUserMsg = deleteUserMsg;
	}
	public UIOutput getCreateUserMsg() {
		return createUserMsg;
	}
	public void setCreateUserMsg(UIOutput createUserMsg) {
		this.createUserMsg = createUserMsg;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String submitUser(){
		User newUser = userDAO.newUser(	user.getUsername(), 
										user.getPassword(), 
										user.getEmail(), 
										user.getFirstname(),
										user.getLastname(),
										user.getTelephone(),
										(boolean) user.isAdmin(), 
										user.getCif());
		if(newUser == null){
			createUserMsg.setRendered(true);
			createUserMsg.setValue("[ERROR] El usuario ya existe.");
		}else{
			createUserMsg.setRendered(true);
			createUserMsg.setValue("[OK] El usuario ha sido creado.");
		}
		
		return null;
	}
	public String deleteUser(){
		User deletedUser = userDAO.removeUser(user.getUsername());
		
		if(deletedUser == null){
			deleteUserMsg.setRendered(true);
			deleteUserMsg.setValue("[ERROR] El usuario no existe.");
		}else{
			deleteUserMsg.setRendered(true);
			deleteUserMsg.setValue("[OK] El usuario ha sido eliminado.");
		}
		
		return null;
	}
}