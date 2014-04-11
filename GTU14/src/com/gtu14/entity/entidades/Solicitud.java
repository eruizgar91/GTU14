package javaeetutorial.dukesbookstore.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package com.gtu14.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@RequestScoped
@Entity
@Table(name="REQUEST")
public class Solicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_request;
    @ManyToOne
	@JoinColumn(name="cif_applicant")
	private Solicitante applicant;
    @ManyToOne
	@JoinColumn(name="cif_bank")
	private Banco bank;
    @ManyToOne
	@JoinColumn(name="cif_university")
	private Universidad university;
    @ManyToOne
	@JoinColumn(name="cif_stamping")
	private Estampadora stamping;
    private Date requestdate;
    private String state;
    private String comment;
    private long cardnumber;
    private String accountnumber;
    
    public Solicitud(){
    	
    }

    public Solicitud(Long id_request, Solicitante applicant, Banco bank,
			Universidad university, Estampadora stamping, Date requestdate,
			String state, String comment, long cardnumber, String accountnumber) {
		this.id_request = id_request;
		this.applicant = applicant;
		this.bank = bank;
		this.university = university;
		this.stamping = stamping;
		this.requestdate = requestdate;
		this.state = state;
		this.comment = comment;
		this.cardnumber = cardnumber;
		this.accountnumber = accountnumber;
	}

	public Long getId_request() {
		return id_request;
	}

	public void setId_request(Long id_request) {
		this.id_request = id_request;
	}

	public Solicitante getApplicant() {
		return applicant;
	}

	public void setApplicant(Solicitante applicant) {
		this.applicant = applicant;
	}

	public Banco getBank() {
		return bank;
	}

	public void setBank(Banco bank) {
		this.bank = bank;
	}

	public Universidad getUniversity() {
		return university;
	}

	public void setUniversity(Universidad university) {
		this.university = university;
	}

	public Estampadora getStamping() {
		return stamping;
	}

	public void setStamping(Estampadora stamping) {
		this.stamping = stamping;
	}

	public Date getRequestdate() {
		return requestdate;
	}

	public void setRequestdate(Date requestdate) {
		this.requestdate = requestdate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(long cardnumber) {
		this.cardnumber = cardnumber;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
        return id_request;
    }

    public void setId(Long id) {
        this.id_request = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id_request != null ? id_request.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.id_request == null && other.id_request != null) || (this.id_request != null && !this.id_request.equals(other.id_request))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gtu14.entity.Solicitud[ id=" + id_request + " ]";
    }
    
}
