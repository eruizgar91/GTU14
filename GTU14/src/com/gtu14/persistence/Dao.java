/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gtu14.persistence;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.gtu14.entity.Banco;
import com.gtu14.entity.Usuario;

@Stateful
public class Dao {
	@PersistenceContext
	private EntityManager em;
	
	public void persist(Usuario usuario){
		em.persist(usuario);
	}
	public void persist(Banco banco){
		em.persist(banco);
	}
	//TODO: Esto son métodos de ejemplo...
}
