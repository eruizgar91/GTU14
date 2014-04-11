package com.gtu14.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Universidad
 *
 */
@Entity
@Table(name="UNIVERSITY")
public class Universidad implements Serializable {

	@Id
	private long cif_university;
    private String name;
	private String email;
	private String address;
	private int telephone;
	@ManyToOne
	@JoinColumn(name="cif_bank")
	private Banco bank;
	private static final long serialVersionUID = 1L;
	public Universidad(){
		
	}
	public Universidad(long cif_university, String name, String email,
			String address, int telephone, Banco bank) {
		this.cif_university = cif_university;
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.bank = bank;
	}
	
	public long getCif_university() {
		return cif_university;
	}
	public void setCif_university(long cif_university) {
		this.cif_university = cif_university;
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
	public Banco getBank() {
		return bank;
	}
	public void setBank(Banco bank) {
		this.bank = bank;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}	

