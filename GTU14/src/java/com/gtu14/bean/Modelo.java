/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gtu14.bean;

import com.gtu14.entity.TipoRol;
import com.gtu14.entity.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author gonzalo
 */
@SessionScoped
@ManagedBean
public class Modelo implements Serializable {
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
        SelectItem[] items = new SelectItem[TipoRol.rol.values().length];
        int i = 0;
        for (TipoRol.rol g : TipoRol.rol.values()) {
            items[i++] = new SelectItem(g, g.toString());
        }
        return items;
    }
}
