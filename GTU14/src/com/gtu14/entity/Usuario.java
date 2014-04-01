/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gtu14.entity;

import com.gtu14.entity.TipoRol.rol;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@SessionScoped
@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Long id;
    @Column(name="NOMBRE")
    private String nombre;
    @Column(name="APELLIDOS")
    private String apellidos;
    @Column(name="ENTIDAD")
    private String entidad;
    @Column(name="CORREO")
    private String correo;
    @Column(name="CONTRASENA")
    private String contrasena;
    @Column(name="ROL")
    private rol rol;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String apellidos, String entidad, String correo, String contrasena, rol rol) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.entidad = entidad;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    
    public rol getRol() {
        return rol;
    }

    public void setRol(rol rol) {
        this.rol = rol;
    }
    
    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
             message="Email incorrecto.")
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gtu14.entity.Usuario[ id=" + id + " ]";
    }
    
}
