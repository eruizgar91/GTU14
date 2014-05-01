package com.gtu14.bean;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
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
										(int) user.getRole(), 
										(long) user.getCif_general());
		if(newUser == null){
			//TODO mostrar error "usuario ya existía"
		}else{
			//TODO mostrar "usuario creado"
		}
		
		return null;
	}
	public String deleteUser(){
		User deletedUser = userDAO.removeUser(user.getUsername());
		
		if(deletedUser == null){
			//TODO mostrar error "usuario no existía"
		}else{
			//TODO mostrar "usuario eliminado: deletedUser"
		}
		
		return null;
	}
}