package com.gtu14.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="APPLICANT")
public class Applicant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String cif_applicant;
    private String firstname;
    private String lastname;
    private int typecif; //0 dni 1 passaporte 
    private Date borndate;
    private String nacionality;
	private String email;
	private String address;
	private String population;
	private String province;
	private String role;  //alumno docente etc.	
	private int telephone;
	@ManyToOne
	@JoinColumn(name="cif_university")
	
	
	private University university;
	public Applicant(){
		
	}
	public Applicant(String cif_applicant, String firstname, String lastname,
			int typecif, Date borndate, String nacionality, String email,
			String address, String population, String province, String role,
			int telephone, University university) {
		this.cif_applicant = cif_applicant;
		this.firstname = firstname;
		this.lastname = lastname;
		this.typecif = typecif;
		this.borndate = borndate;
		this.nacionality = nacionality;
		this.email = email;
		this.address = address;
		this.population = population;
		this.province = province;
		this.role = role;
		this.telephone = telephone;
		this.university = university;
	}
	public String getCif_applicant() {
		return cif_applicant;
	}
	public void setCif_applicant(String cif_applicant) {
		this.cif_applicant = cif_applicant;
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
	public int getTypecif() {
		return typecif;
	}
	public void setTypecif(int typecif) {
		this.typecif = typecif;
	}
	public Date getBorndate() {
		return borndate;
	}
	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}
	public String getNacionality() {
		return nacionality;
	}
	public void setNacionality(String nacionality) {
		this.nacionality = nacionality;
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
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
