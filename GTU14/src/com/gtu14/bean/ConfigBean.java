/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.gtu14.bean;

import java.sql.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.PersistenceContext;

import com.gtu14.bean.EntityModel.entityRole;
import com.gtu14.entity.Applicant;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Request;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.persistence.EntityDAO;

/**
 * <p>Singleton bean that initializes the book database for the bookstore
 * example.</p>
 */
@Singleton
@Startup
public class ConfigBean {

	@EJB
	private EntityDAO request;
	


	@PostConstruct
	public void createData() {
		
		//Crear una estampadora
		Stamping auxStamping=request.newStamping("cifEstampadora", "Estampadora", "estampadora@gmail.com", "Avenida",722539834);
		if(auxStamping==null)		
		System.out.println("Error al crear estampadora");
		
		//Crear un banco
				Bank auxBank=request.newBank("cifBanco", "Banco", "banco@gmail.com", "Avenida complutense",722539845,auxStamping);
				if(auxBank==null)		
				System.out.println("Error al crear banco");
				
	    //Crear una Universidad
				University auxUniversity=request.newUniversity("cifUniversidad", "Universidad", "universidad@gmail.com", "Avenida paraninfo",722539578,auxBank);
				if(auxUniversity==null)		
				System.out.println("Error al crear banco");
		//Solicitante
				Applicant auxaApplicant=new Applicant(
						"G12364489","Brian","Vazquez",1,new Date(19922699),"Mexico","brian@gmail.com","Calle villagarica 2","Madrid","Madrid","Estudiante",525535596,auxUniversity);
				Request auxRequest= new Request();
				System.out.println(auxaApplicant.getBorndate());
				

	}
}
