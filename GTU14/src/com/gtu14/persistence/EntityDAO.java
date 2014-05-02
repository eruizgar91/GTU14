package com.gtu14.persistence;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.bean.EntityModel.entityRole;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;

/**
 * Objeto de acceso a datos (DAO) que maneja las entidades
 * (jurídicas): Universidad, banco y estampadora.
 * 
 * @author Gonzalo Pérez-Tomé Estévez
 *
 */
@Stateful
public class EntityDAO { //Entidad = Universidad || Banco || Estampadora
	@PersistenceContext
	private EntityManager em;
	
	/** 
	 * Crea una entidad universidad de la base de datos.
	 * 
	 * @param 	cif_university		Cif de la entidad universidad a crear.
	 * @return	newUniversity		Universidad creada o null si no existía antes.
	 */
	public University newUniversity(String cif_university, String name, String email,
			String address, int telephone, Bank bank) {
		
		//Vemos si existe
		if(findUniversity(cif_university) != null)
			return null;
		//Si no, la creamos.
		University newUniversity = new University(cif_university,name, email, address, telephone, bank);
		try {
			em.persist(newUniversity);
		} catch (Exception e) {
			return null;
		}
		return newUniversity;
	}
	/** 
	 * Crea una entidad banco de la base de datos.
	 * 
	 * @param 	cif_bank		Cif de la entidad banco a crear.
	 * @return	newBank		Banco creado o null si no existía antes.
	 */
	public Bank newBank(String cif_bank, String name, String email,
			String address, int telephone, Stamping stamping) {
		//Vemos si existe
		if(findBank(cif_bank) != null)
			return null;
		//Si no, lo creamos.	
		Bank newBank = new Bank(cif_bank,name, email, address, telephone, stamping);
		try {
			em.persist(newBank);
		} catch (Exception e) {
			return null;
		}
		return newBank;
	}
	/** 
	 * Crea una entidad estampadora de la base de datos.
	 * 
	 * @param 	cif_stamping		Cif de la entidad estampadora a crear.
	 * @return	newStamping			Estampadora creada o null si no existía antes.
	 */	
	public Stamping newStamping(String cif_stamping, String name, String email,
			String address, int telephone) {
		//Vemos si existe
		if(findStamping(cif_stamping) != null)
			return null;
		//Si no, la creamos.			
		Stamping newStamping = new Stamping(cif_stamping, name, email, address, telephone);
		try {
			em.persist(newStamping);
		} catch (Exception e) {
			return null;
		}
		return newStamping;
	}

	
	/** 
	 * Elimina una entidad universidad de la base de datos.
	 * 
	 * @param 	cif_university		Cif de la entidad universidad a borrar.
	 * @return	deletedUniversity	Universidad borrada o null si no existía antes.
	 */
	public University deleteUniversity(String cif_university){
		University deletedUniversity = findUniversity(cif_university);
		if(deletedUniversity == null) //No existe la universidad
			return null;
		
		try{
		em.remove(deletedUniversity); //Eliminamos la universidad
		}catch(Exception ex){
		}
		return deletedUniversity;
	}
	/** 
	 * Elimina una entidad banco de la base de datos.
	 * 
	 * @param 	cif_bank			Cif de la entidad banco a borrar.
	 * @return	deletedBank			Banco borrado o null si no existía antes.
	 */
	public Bank deleteBank(String cif_bank){
		Bank deletedBank = findBank(cif_bank);
		if(deletedBank == null) //No existe el banco
			return null;
		
		try{
		em.remove(deletedBank); //Eliminamos el banco
		}catch(Exception ex){
		}
		return deletedBank;
	}
	/** 
	 * Elimina una entidad estampadora de la base de datos.
	 * 
	 * @param 	cif_stamping			Cif de la entidad estampadora a borrar.
	 * @return	deletedStamping			Estampadora borrada o null si no existía antes.
	 */	
	public Stamping deleteStamping(String cif_stamping){
		Stamping deletedStamping = findStamping(cif_stamping);
		if(deletedStamping == null) //No existe la estampadora
			return null;
		
		try{
		em.remove(deletedStamping); //Eliminamos la estampadora
		}catch(Exception ex){
		}
		return deletedStamping;
	}
	
	/** 
	 * Busca una entidad Universidad de la base de datos.
	 * 
	 * @param 	cif_university			Cif de la entidad universidad a encontrar.
	 * @return	findedUniversity		Universidad encontrada o null si no existía antes.
	 */	
	public University findUniversity(String cif_university){
		try{
			University findedUniversity = em.find(University.class, cif_university);
			return findedUniversity;
		}catch(Exception ex){
			return null;
		}
	}
	/** 
	 * Busca una entidad Banco de la base de datos.
	 * 
	 * @param 	cif_bank	Cif de la entidad banco a encontrar.
	 * @return	findedBank	Banco encontrado o null si no existía antes.
	 */	
	public Bank findBank(String cif_bank){
		try{
			Bank findedBank = em.find(Bank.class, cif_bank);
			return findedBank;
		}catch(Exception ex){
			return null;
		}
	}
	/** 
	 * Busca una entidad Estampadora de la base de datos.
	 * 
	 * @param 	cif_stamping	Cif de la entidad estampadora a encontrar.
	 * @return	findedStamping	Estampadora encontrada o null si no existía antes.
	 */	
	public Stamping findStamping(String cif_stamping){
		try{
			Stamping findedStamping = em.find(Stamping.class, cif_stamping);
			return findedStamping;
		}catch(Exception ex){
			return null;
		}
	}

	
	/**
	 * Devuelve el rol de una entidad de la bb.dd.
	 * 
	 * @param 	cif		Cif de la entidad.
	 * @return	rol		Universidad || Banco || Estampadora. Null si no existe en la bb.dd
	 */
	public entityRole getEntityRole(String cif){
		if(findUniversity(cif) != null)
			return entityRole.UNIVERSITY;
		if(findBank(cif) != null) 
			return entityRole.BANK;
		if(findStamping(cif) != null) 
			return entityRole.STAMPING;
		return null;
	}
}
