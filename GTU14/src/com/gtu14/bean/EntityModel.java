package com.gtu14.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.inject.Named;

import com.gtu14.entity.Bank;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.persistence.EntityDAO;
import com.sun.xml.ws.developer.Stateful;

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
	
	private UIOutput createEntityMsg;
	private UIOutput deleteEntityMsg;
	
	private entityRole entityRole;
	private String entityname;
	private String name;
	private String cif;
	private String associatedCif;
	private String email;
	private String password;
	private String address;
	private int telephone;
	
	public UIOutput getCreateEntityMsg() {
		return createEntityMsg;
	}
	public void setCreateEntityMsg(UIOutput createEntityMsg) {
		this.createEntityMsg = createEntityMsg;
	}
	public UIOutput getDeleteEntityMsg() {
		return deleteEntityMsg;
	}
	public void setDeleteEntityMsg(UIOutput deleteEntityMsg) {
		this.deleteEntityMsg = deleteEntityMsg;
	}
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
		createEntityMsg.setRendered(true);
		
		//Vemos si existe ya una entidad con ese cif
		com.gtu14.bean.EntityModel.entityRole existingEntityRole = entityDao.getEntityRole(this.cif);
		if(existingEntityRole != null){
			switch (existingEntityRole) {
			case UNIVERSITY:
				createEntityMsg.setValue("[ERROR] Ya existe una universidad con ese cif.");
				break;
			case BANK:
				createEntityMsg.setValue("[ERROR] Ya existe un banco con ese cif.");
				break;
			case STAMPING:
				createEntityMsg.setValue("[ERROR] Ya existe una estampadora con ese cif.");
				break;
			}
			return null;
		}
		
		//Si no, la creamos.
		switch (this.entityRole) {
		case UNIVERSITY:
			if(submitUniversity() != null){
				createEntityMsg.setValue("[OK] Universidad creada.");
			}else{
				createEntityMsg.setValue("[ERROR] Ya existe la universidad.");
			}
			break;
		case BANK:
			if(submitBank() != null){
				createEntityMsg.setValue("[OK] Banco creado.");
			}else{
				createEntityMsg.setValue("[ERROR] Ya existe el banco.");
			}
			break;
		case STAMPING:
			if(submitStamping() != null){
				createEntityMsg.setValue("[OK] Estampadora creada.");
			}else{
				createEntityMsg.setValue("[ERROR] Ya existe la estampadora.");
			}
			break;
		default:
			createEntityMsg.setValue("[ERROR] Información inválida.");
			break; //No hacer nada.
		}
		return null;
	}
	/**
	 * Da de baja una entidad universidad, banco o estampadora con dado el cif en el .xhtml
	 */
	public String deleteEntity(){
		deleteEntityMsg.setRendered(true);
		//Vemos si existe
		entityRole role = entityDao.getEntityRole(this.cif);
		if(role == null){
			deleteEntityMsg.setValue("[ERROR] No existe la entidad.");
			return null;
		}
		
		switch (entityDao.getEntityRole(this.cif)) {
		case UNIVERSITY:
			if(entityDao.deleteUniversity(this.cif) != null)
				deleteEntityMsg.setValue("[OK] Universidad borrada.");
			break;
		case BANK:
			if(entityDao.deleteBank(this.cif) != null)
				deleteEntityMsg.setValue("[OK] Banco borrado.");
			break;
		case STAMPING:
			if(entityDao.deleteStamping(this.cif) != null)
				deleteEntityMsg.setValue("[OK] Estampadora borrada.");
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
