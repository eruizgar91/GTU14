package com.gtu14.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.gtu14.entity.Bank;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.persistence.EntityDAO;

/**
 * Modelo para el manejo de propiedades relacionadas con
 * universidad, banco o estampadora (entidades jurídicas).
 * 
 * @author Gonzalo Pérez-Tomé Estévez
 *
 */
@Named
@RequestScoped
public class EntityModel implements Serializable{ //Entidad = Universidad || Banco || Estampadora
	
	private static final long serialVersionUID = -2633611667469166418L;
	
	public enum entityRole {UNIVERSITY, BANK, STAMPING};
	
	@EJB
	private EntityDAO entityDao;
	
	private entityRole entityRole;
	private String entityname;
	private String name;
	private String cif;
	private String associatedCif;
	private String email;
	private String password;
	private String address;
	private int telephone;
	
	public String getEntityname() {
		return entityname;
	}
	public void setEntityname(String entityname) {
		this.entityname = entityname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getAssociatedCif() {
		return associatedCif;
	}
	public void setAssociatedCif(String associatedCif) {
		this.associatedCif = associatedCif;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public entityRole getEntityRole() {
		return entityRole;
	}
	public void setEntityRole(entityRole entityRole) {
		this.entityRole = entityRole;
	}

	/**
	 * Da de alta una entidad universidad, banco o estampadora con datos del .xhtml
	 */
	public String submitEntity(){
		switch (this.entityRole) {
		case UNIVERSITY:
			submitUniversity();
			break;
		case BANK:
			submitBank();
			break;
		case STAMPING:
			submitStamping();
			break;
		default:
			break; //Se ha enviado un valor inválido. No hacer nada.
		}
		return null;
	}
	/**
	 * Da de baja una entidad universidad, banco o estampadora con dado el cif en el .xhtml
	 */
	public String deleteEntity(){
		//Vemos si existe
		entityRole role = entityDao.getEntityRole(this.cif);
		if(role == null)
			return null; //No existe.
		
		switch (entityDao.getEntityRole(this.cif)) {
		case UNIVERSITY:
			entityDao.deleteUniversity(this.cif);
			break;
		case BANK:
			entityDao.deleteBank(this.cif);
			break;
		case STAMPING:
			entityDao.deleteStamping(this.cif);
			break;
		}
		return null;
	}
	
	/**
	 * Intenta crear la entidad universidad con los parámetros del .xhtml
	 * 
	 * @return	newUniversity	Universidad creada o null si ya existía.
	 */
	private University submitUniversity(){
		//Vemos si existe el banco asociado
		Bank bankTmp = entityDao.findBank(this.associatedCif);
		if(bankTmp == null){
			//TODO El banco asociado no existe. Se inserta vacío ese campo en la bb.dd.
		}

		University newUniversity = entityDao.newUniversity(	this.cif,
															this.name,
															this.email,
															this.address,
															this.telephone,
															bankTmp);
		return newUniversity;
	}
	/**
	 * Intenta crear la entidad banco con los parámetros del .xhtml
	 * 
	 * @return	newBank	Banco creado o null si ya existía.
	 */
	private Bank submitBank(){
		//Vemos si existe la estampadora asociada.
		Stamping stampingTmp = entityDao.findStamping(this.associatedCif);
		if(stampingTmp == null){
			//TODO La estampadora asociada no existe. Se inserta ese campo vacío en la bb.dd.
		}
	
		Bank newBank = entityDao.newBank(	this.cif,
											this.name,
											this.email,
											this.address,
											this.telephone,
											stampingTmp);	
		return newBank;
	}
	/**
	 * Intenta crear la entidad estampadora con los parámetros del .xhtml
	 * 
	 * @return	newStamping	Estampadora creada o null si ya existía.
	 */
	private Stamping submitStamping(){

		Stamping newStamping = entityDao.newStamping(	this.cif,
														this.name,
														this.email,
														this.address,
														this.telephone);	
		return newStamping;
	}
	
	
}
