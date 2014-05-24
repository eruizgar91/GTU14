package com.gtu14.persistence;

import java.util.Date;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.entity.Applicant;
import com.gtu14.entity.University;

@Stateful
public class ApplicantDAO {
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Crea un solicitante en la base de datos y su entidad correspondiente.
	 * 
	 * @return	newApplicant	Solicitante creado o null si ya existía antes.
	 */
	public Applicant newApplicant(String cifApplicant, 
						String address, 
						Date bornDate,
						String email,
						String firstname,
					    String lastname,
					    String nationality,
					    String population,
					    String province,
					    String role,
					    int typeCif,
					    int telephone,
					    University university){
		//Vemos si existe el solicitante
		if(findApplicant(cifApplicant) != null)
			return null;
		
		Applicant newApplicant = new Applicant(cifApplicant,firstname,lastname,typeCif,bornDate,nationality,email,address,population,province,role,telephone,university);
		try{
			em.persist(newApplicant);
		}catch(Exception ex){
			return null;
		}
		return newApplicant;
	}
	
	/**
	 * Crea un solicitante en la base de datos y su entidad correspondiente.
	 * 
	 * @return	newApplicant	Solicitante creado o null si ya existía antes.
	 */
	public Applicant newApplicant(Applicant newApplicant){
		//Vemos si existe el solicitante
		if(findApplicant(newApplicant.getCif_applicant()) != null)
			return null;
		
		try{
			em.persist(newApplicant);
		}catch(Exception ex){
			return null;
		}
		return newApplicant;
	}
	
	/**
	 * Busca un usuario de la base de datos.
	 * 
	 * @param 	username	Nombre del usuario.
	 * @return	resultUser	Usuario encontrado o null.
	 */
	private Applicant findApplicant(String cifApplicant){
		try{
			Applicant resultApplicant = em.find(Applicant.class, cifApplicant);
			return resultApplicant;
		}catch(Exception ex){
			return null;
		}
		
	}
}
