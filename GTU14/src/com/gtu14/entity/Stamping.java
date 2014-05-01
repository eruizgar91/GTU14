package com.gtu14.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Estampadora
 *
 */
@Entity
@Table(name="STAMPING")
public class Stamping implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	private String cif_stamping;
	private String name;
	private String email;
	private String address;
	private int telephone;
	
	public Stamping(){}
	public Stamping(String cif_stamping, String name, String email, String address,
			int telephone) {
		this.cif_stamping = cif_stamping;
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
	}
	public String getCif_stamping() {
		return cif_stamping;
	}
	public void setCif_stamping(String cif_stamping) {
		this.cif_stamping = cif_stamping;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

	