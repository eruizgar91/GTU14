/**
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package com.gtu14.bean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.gtu14.entity.Applicant;
import com.gtu14.entity.Bank;
import com.gtu14.entity.Request;
import com.gtu14.entity.Stamping;
import com.gtu14.entity.University;
import com.gtu14.entity.User;
import com.gtu14.persistence.ApplicantDAO;
import com.gtu14.persistence.EntityDAO;
import com.gtu14.persistence.RequestDAO;
import com.gtu14.persistence.UserDAO;
import com.sun.istack.logging.Logger;

/**
 * <p>
 * Singleton bean that initializes the book database for the bookstore example.
 * </p>
 */
@Singleton
@Startup
public class ConfigBean {

	private static final Logger logger = Logger.getLogger("Login log",
			AccessControlModel.class);
	@EJB
	private EntityDAO entityDao;
	@EJB
	private UserDAO userDao;
	@EJB
	private ApplicantDAO applicantDao;
	@EJB
	private RequestDAO requestDao;

	@PostConstruct
	public void createData() {

		// Crear una estampadora
		Stamping auxStamping = entityDao.newStamping("cifEstampadora",
				"Estampadora", "estampadora@gmail.com", "Avenida", 722539834);
		if (auxStamping == null)
			logger.log(Level.SEVERE, "Error al crear estampadora");

		// Crear un banco
		Bank auxBank = entityDao.newBank("cifBanco", "Banco",
				"banco@gmail.com", "Avenida complutense", 722539845,
				auxStamping);
		if (auxBank == null)
			logger.log(Level.SEVERE, "Error al crear banco");

		// Crear una Universidad
		University auxUniversity = entityDao.newUniversity("cifUniversidad",
				"Universidad", "universidad@gmail.com", "Avenida paraninfo",
				722539578,"ETSIT" ,auxBank);
		if (auxUniversity == null)
			logger.log(Level.SEVERE, "Error al crear la universidad");

		// Crear un usuario administrador
		User adminUser = userDao.newUser("root", "root", "admin1t@gtu14.com",
				"Gonzalo", "Perez-Tome", 123456789, true, "");
		if (adminUser == null)
			logger.log(Level.SEVERE, "Error al crear el administrador");
		
		User bankUser = userDao.newUser("banco", "banco", "banco@gmail.com",
				"Banco", "b", 916667788, false, "cifBanco");
		if (bankUser == null)
			logger.log(Level.SEVERE, "Error al crear el administrador");
		User stampingUser = userDao.newUser("estampadora", "estampadora", "estampadora@gmail.com",
				"estampa", "e", 916667788, false, "cifEstampadora");
		if (stampingUser == null)
			logger.log(Level.SEVERE, "Error al crear el stamping");

		// Crear un Solicitante

		Applicant auxApplicant = applicantDao.newApplicant(new Applicant(
				"G12364489", "Brian", "Vazquez", 1, new Date(19920992),
				"Mexico", "brian@gmail.com", "Calle villagarica 2", "Madrid",
				"Madrid", "Estudiante", 525535596, auxUniversity));
		if (auxApplicant == null)
			logger.log(Level.SEVERE, "Error al crear el solicitante");

		// Crear una solicitud
		 
		  Request auxRequest = requestDao.newRequest((long) 1,
				"2342-3454-6756-7865", auxApplicant, auxBank, auxUniversity,
				auxStamping, "Comentario bla bla..", new Date(0),
				"estado en pruebas", (long) 1234123412);
		if (auxRequest == null)
			logger.log(Level.SEVERE, "Error al crear la solicitud");
		
		// Crear una solicitud
		 
		 Applicant aux=applicantDao.newApplicant(new Applicant(
					"12232234", "Jorge", "Ulloa", 1, new Date(19920992),
					"España", "jorge@gmail.com", "Calle villagarica 2", "Madrid",
					"Madrid", "Estudiante", 855535596, auxUniversity));
				Request auxRequest1 = requestDao.newRequest((long) 2,
						"1234-1234-1234-1234", aux, auxBank, auxUniversity,
						auxStamping, "Comentario bla bla..", new Date(0),
						"estado en pruebas", (long) 1234123412);
				if (auxRequest1 == null)
					logger.log(Level.SEVERE, "Error al crear la solicitud");
				

	}
}
