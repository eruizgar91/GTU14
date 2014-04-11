package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="FACULTY_UNIVERSITY")
 class Facultad implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="cif_university")
	private Universidad university;
	@Id
	private String faculty;
	public Facultad(){
		
	}
	public Facultad(Universidad university, String faculty) {
		this.university = university;
		this.faculty = faculty;
	}
	public Universidad getUniversity() {
		return university;
	}
	public void setUniversity(Universidad university) {
		this.university = university;
	}
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}