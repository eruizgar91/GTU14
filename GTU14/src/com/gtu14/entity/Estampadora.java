package com.gtu14.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Estampadora
 *
 */
@Entity
@Table(name="ESTAMPADORA")
public class Estampadora implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
	private long ID;
	private String Nombre;
	private String CIF;
	private static final long serialVersionUID = 1L;

	public Estampadora() {
		super();
	}   
	public long getID() {
		return this.ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}   
	public String getNombre() {
		return this.Nombre;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}   
	public String getCIF() {
		return this.CIF;
	}

	public void setCIF(String CIF) {
		this.CIF = CIF;
	}
   
}
