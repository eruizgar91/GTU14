package com.gtu14.bean;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.gtu14.bean.EntityModel.entityRole;
import com.gtu14.entity.Request;
import com.gtu14.entity.University;
import com.gtu14.entity.User;
import com.gtu14.persistence.EntityDAO;
import com.gtu14.persistence.RequestDAO;
import com.gtu14.persistence.UserDAO;
import com.sun.istack.logging.Logger;

@Named
@RequestScoped
public class AccessControlModel implements Serializable {
	private static final long serialVersionUID = -1247134159102289644L;
	private static final Logger logger = Logger.getLogger("Login log",AccessControlModel.class);

	@EJB
	private UserDAO userDao;
	@EJB
	private EntityDAO entityDao;
	@EJB
	private RequestDAO requestDao;
	@Inject
	private User user;
	private UIOutput wrongPassword;
	
	
	public UIOutput getWrongPassword() {
		return wrongPassword;
	}
	public void setWrongPassword(UIOutput wrongPassword) {
		this.wrongPassword = wrongPassword;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Crea una sesión si los datos del formulario login son válidos.
	 */
	public String submitLogin(){
		//Invalidamos la sesión que hubiese.
		this.logout();
		//Comprobamos el usuario.
		User validatedUser = userDao.validateUser(user.getUsername(), user.getPassword());
		if(validatedUser == null){
			logger.log(Level.WARNING, "Usuario o contraseña incorrectos.");
			wrongPassword.setRendered(true);
			return null;
		}
		//Creamos la sesión si el usuario es válido.
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		logger.log(Level.INFO, "Sesión de usuario creada con éxito. SessionID = {0}", session.getId());
		
		//Redireccionamos a la página según el rol del usuario.
		if(validatedUser.isAdmin())
			return "Administrador";
		
		entityRole userRole = entityDao.getEntityRole(validatedUser.getCif());
		switch (userRole) {
		case UNIVERSITY:
			return "Universidad"; //TODO Nombre del xhtml a redireccionar cuando sea un usuario de una universidad.
		case BANK:
			return "Banco"; //TODO Nombre del xhtml a redireccionar cuando sea un usuario de un banco.
		case STAMPING:
			return "Estampadora"; //TODO Nombre del xhtml a redireccionar cuando sea un usuario de una estampadora.
		default:
			return null;
		}
	}
	/**
	 * Cierra la sesión que hubiese en curso.
	 */
	public void logout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(session != null)
			session.invalidate();
	}
}
