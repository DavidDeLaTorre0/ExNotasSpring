package org.agaray.notas.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno {

	//=================================
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String apellido;
	
	@OneToMany(mappedBy = "alumno")
	private Collection<Nota> notas;
	//=================================
	public Alumno() {
		this.notas = new ArrayList<>();
	}
	public Alumno(String nombre, String apellido) {
		super();
		this.notas = new ArrayList<>();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	//=================================

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Collection<Nota> getNotas() {
		return notas;
	}
	public void setNotas(Collection<Nota> notas) {
		this.notas = notas;
	}
	
	
	//=================================
	
	


}
