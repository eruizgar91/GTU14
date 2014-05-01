package com.gtu14.persistence;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.entity.User;

@Stateful
public class UserDAO {
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Crea un usuario en la base de datos y su entidad correspondiente.
	 * 
	 * @param 	username	Nombre del usuario.
	 * @return	newUser		Usuario creado o null si ya existía antes.
	 */
	public User newUser(String username, 
						String password, 
						String email,
						String firstname,
					    String lastname,
						int telephone,
						boolean admin,
						String cif){
		//Vemos si existe
		if(findUser(username) != null)
			return null;
		
		User newUser = new User(username,password,email,firstname,lastname,telephone,admin,cif);
		try{
			em.persist(newUser);
		}catch(Exception ex){
			return null; //Ya existe ese usuario
		}
		return newUser;
	}
	/**
	 * Elimina un usuario de la base de datos y su entidad correspondiente.
	 * 
	 * @param 	username	Nombre del usuario.
	 * @return	userTmp		Usuario borrado o null si no existía antes.
	 */
	public User removeUser(String username){
		User userTmp = findUser(username);
		if(userTmp == null) //No existe el usuario
			return null;
		
		try{
		em.remove(userTmp); //Eliminamos el usuario
		}catch(Exception ex){
		}
		
		return userTmp;
	}
	/**
	 * Busca un usuario de la base de datos.
	 * 
	 * @param 	username	Nombre del usuario.
	 * @return	resultUser	Usuario encontrado o null.
	 */
	public User findUser(String username){
		try{
			User resultUser = em.find(User.class, username);
			return resultUser;
		}catch(Exception ex){
			return null;
		}
		
	}
}
