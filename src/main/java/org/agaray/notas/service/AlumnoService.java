package org.agaray.notas.service;

import java.util.List;

import org.agaray.notas.entities.Alumno;
import org.agaray.notas.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoService {

	@Autowired
	private AlumnoRepository alumnoRepository;

	public void save(String nombre, String apellido) throws Exception {
		try {
			alumnoRepository.save(new Alumno(nombre,apellido));
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

	public List<Alumno> findAll() {
		return alumnoRepository.findAll();
	}

	public Alumno  getById(Long idAlumno) {
		return alumnoRepository.getById(idAlumno);
	}
}