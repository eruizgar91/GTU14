package com.gtu14.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Banco
 *
 */
@Entity
@Table(name="BANK")
public class Bank implements Serializable {

    @Id
	private String cif_bank;
    private String name;
	private String email;
	private String address;
	private int telephone;
	@ManyToOne
	@JoinColumn(name="cif_stamping")
	private Stamping stamping;
	private static final long serialVersionUID = 1L;
	public Bank(){
		
	}
	public Bank(String cif_bank, String name, String email, String address,
			int telephone, Stamping stamping) {
		this.cif_bank = cif_bank;
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.stamping = stamping;
	}
	public String getCif_bank() {
		return cif_bank;
	}
	public void setCif_bank(String cif_bank) {
		this.cif_bank = cif_bank;
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
	public Stamping getStamping() {
		return stamping;
	}
	public void setStamping(Stamping stamping) {
		this.stamping = stamping;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}