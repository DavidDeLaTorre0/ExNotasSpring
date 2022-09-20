package org.agaray.notas.service;

import java.util.List;

import org.agaray.notas.entities.Alumno;
import org.agaray.notas.entities.Asignatura;
import org.agaray.notas.entities.Nota;
import org.agaray.notas.repository.AlumnoRepository;
import org.agaray.notas.repository.AsignaturaRepository;
import org.agaray.notas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaService {

	@Autowired
	private NotaRepository notaRepository;

	@Autowired
	private AlumnoRepository alumnoRepository;

	@Autowired
	private AsignaturaRepository asignaturaRepository;

	public void save(Long idAlumno,Long idAsignatura,int numero) throws Exception {
		try {
			Nota nota = new Nota(numero);
			Alumno alumno = alumnoRepository.getById(idAlumno);
			Asignatura asignatura = asignaturaRepository.getById(idAsignatura);
			
			nota.setAlumno(alumno);
			nota.setAsignatura(asignatura);
			
			alumno.getNotas().add(nota);
			asignatura.getNotas().add(nota);
			
			notaRepository.save(nota);
		}
		catch (Exception e) {
			throw new Exception("Algo ha ido mal al guardar la nota");
		}
	}

	public List<Nota> findAll() {
		return notaRepository.findAll();
	}
}