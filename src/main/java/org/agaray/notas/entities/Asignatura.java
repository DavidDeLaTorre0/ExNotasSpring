package org.agaray.notas.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Asignatura {
	
	//=================================

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	//cada equipo tendra una collecion de los que ha jugado como local y de los que ha jugado como visitante,
	//siempre desde el lado uno muchos
	//Y dentro del mapped se le pone como se me conce en el otro lgar, en este caso local 
	//Si es muhcos a muchos da igual donde lo pongas,(en EQUIPO O PARTIDO)
	@OneToMany(mappedBy = "asignatura")
	private Collection<Nota> notas;
	
	//=================================
	//En el lado donde estan las relacciones QUE NO SE TE OLVIDE INICIALIZAR LOS CONTRUCTORES(DE LOCAL Y VISITANTE)
	//y lo hacemos en los dos
	public Asignatura() {
		this.notas = new ArrayList<>();
	}

	public Asignatura(String nombre) {
		super();
		this.notas = new ArrayList<>();
		this.nombre = nombre;
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

	public Collection<Nota> getNotas() {
		return notas;
	}

	public void setNotas(Collection<Nota> notas) {
		this.notas = notas;
	}
	
	
	//=================================
	

}
