/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtu14.bean;

import com.gtu14.entity.Tipos;
import com.gtu14.entity.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@SessionScoped
@ManagedBean
public class Modelo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

    public Modelo() {
        //TODO: Preguntar al DAO e inicializar aquí Usuario y Solicitud.
        this.usuario = new Usuario();
        //this.usuario = new Usuario((long) 55, "Nombre", "Apellidos", "Entidad", "Correo@c.c", "Contraseña", TipoRol.rol.BANCO);
    }

    public Usuario getUsuario() {
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
