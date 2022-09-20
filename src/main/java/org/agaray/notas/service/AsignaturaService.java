package org.agaray.notas.service;

import java.util.List;

import org.agaray.notas.entities.Asignatura;
import org.agaray.notas.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {

	@Autowired
	private AsignaturaRepository asignaturaRepository;

	public void save(String nombre) throws Exception {
		try {
			asignaturaRepository.save(new Asignatura(nombre));
		}
		catch (Exception e) {
			throw new Exception("La asignatura "+nombre+" ya existe");
		}
	}

	public List<Asignatura> findAll() {
		return asignaturaRepository.findAll();
	}

	public Asignatura getById(Long idAsignatura) {
		return asignaturaRepository.getById(idAsignatura);
	}
}