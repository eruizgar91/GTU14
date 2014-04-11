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
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;
	private String password;
	private String email;
    private int role; // 0  Universidad,1 Banco,2 Estampadora,3 Administrador
    private long cif_general; // si role 3 entonces este campo va vacio

    public Usuario() {
    }

	public Usuario(String username, String password, String email, int role,
			long cif_general) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.cif_general = cif_general;
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

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getCif_general() {
		return cif_general;
	}

	public void setCif_general(long cif_general) {
		this.cif_general = cif_general;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
