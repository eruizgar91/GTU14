/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtu14.bean;

import com.gtu14.entity.Tipos;
import com.gtu14.entity.Usuario;
import com.gtu14.persistence.Dao;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

@SessionScoped
@ManagedBean
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	@Inject
	private Dao dao;

    public Modelo() {
        //TODO: Preguntar al DAO e inicializar aquí Usuario y Solicitud.
    }

    public Usuario getUsuario() {
    	//Ejemplo: Se ejecuta este método cuando desde Administrador.xhtml
    	//se llama a "#{modelo.usuario.X}" (lenguaje EL).
    	
    	//Esto no tiene sentido, sólo crea una entrada (con valores arbitrarios)
    	//en la tabla USER usando el DAO.
    	
    	Random r = new Random(); 
    	long rndNumber = r.nextInt(1000);
    	
    	this.usuario = new Usuario(""+rndNumber,""+rndNumber,""+rndNumber,(int)rndNumber, rndNumber );
        
    	dao.persist(this.usuario);
        
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public SelectItem[] getRolValues() {
        SelectItem[] items = new SelectItem[Tipos.rol.values().length];
        int i = 0;
        for (Tipos.rol g : Tipos.rol.values()) {
            items[i++] = new SelectItem(g, g.toString());
        }
        return items;
    }
    public SelectItem[] getFuncionValues() {
        SelectItem[] items = new SelectItem[Tipos.funcion.values().length];
        int i = 0;
        for (Tipos.funcion g : Tipos.funcion.values()) {
            items[i++] = new SelectItem(g, g.toString());
        }
        return items;
    }
}
