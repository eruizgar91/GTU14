package javaeetutorial.dukesbookstore.entity;

//package com.gtu14.entity;

import java.awt.List;
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
public class Estampadora implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	private long cif_stamping;
	private String name;
	private String email;
	private String address;
	private int telephone;
	
	public Estampadora(){
		
	}
	public Estampadora(long cif_stamping, String email, String address,
			int telephone) {
		this.cif_stamping = cif_stamping;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
	}
	public long getCif_stamping() {
		return cif_stamping;
	}
	public void setCif_stamping(long cif_stamping) {
		this.cif_stamping = cif_stamping;
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

	