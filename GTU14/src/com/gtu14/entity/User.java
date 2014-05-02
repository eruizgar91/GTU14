package com.gtu14.entity;

//package com.gtu14.entity;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SessionScoped
@Entity
@Table(name="USERS")// USER no se puede usar 
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;
	private String password;
	private String email;
	private String firstname;
    private String lastname;
	private int telephone;
    private boolean admin; // 0  usuario 1 Administrador
    private String cif; // si admin 1 entonces este campo va vacio, si no, se pone el cif de la entidad que va a gestionar(universidad,estampadora,banco)

    public User() {
    }

	

	public User(String username, String password, String email,
			String firstname, String lastname, int telephone, boolean admin,
			String cif) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.telephone = telephone;
		this.admin = admin;
		this.cif = cif;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin= admin;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif= cif;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

    
}
