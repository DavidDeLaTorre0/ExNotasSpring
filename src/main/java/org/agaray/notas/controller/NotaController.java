package org.agaray.notas.controller;

import org.agaray.notas.entities.Alumno;
import org.agaray.notas.entities.Asignatura;
import org.agaray.notas.exception.DangerException;
import org.agaray.notas.exception.PRG;
import org.agaray.notas.service.AlumnoService;
import org.agaray.notas.service.AsignaturaService;
import org.agaray.notas.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/nota")
public class NotaController {

	@Autowired
	private NotaService notaService;

	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private AsignaturaService asignaturaService;

	@GetMapping("r")
	public String r(
			@RequestParam(value="idAlumno",required = false) Long idAlumno,
			@RequestParam(value="idAsignatura",required = false) Long idAsignatura,
			ModelMap m
			) {
		if (idAlumno==null && idAsignatura==null)  {
			m.put("notas", notaService.findAll());
		}
		else {
			if (idAlumno != null) {
				Alumno alumno =  alumnoService.getById(idAlumno);
				m.put("alumno", alumno);
				m.put("notas", alumno.getNotas());
			}
			if (idAsignatura != null) {
				Asignatura asignatura =  asignaturaService.getById(idAsignatura);
				m.put("asignatura", asignatura);
				m.put("notas", asignatura.getNotas());
			}
		}
		m.put("view", "nota/r");
		return "_t/frame";
	}

	@GetMapping("c")
	public String c(ModelMap m) {
		m.put("alumnos", alumnoService.findAll());
		m.put("asignaturas", asignaturaService.findAll());
		m.put("view", "nota/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(
			@RequestParam(value = "idAlumno", required = false) Long idAlumno,
			@RequestParam(value = "idAsignatura", required = false) Long idAsignatura,
			@RequestParam(value = "numero", required = false) int numero
			) throws DangerException {
		try {
			if (idAlumno == null || idAsignatura==null ) {
				throw new Exception("Las asignaturas / alumnos no pueden ser nulas");
			}
			if (numero<0 || numero>10) {
				throw new Exception("La nota debe estar comprendida entre 0..10");
			}
			notaService.save(idAlumno,idAsignatura,numero);

		} catch (Exception e) {
			PRG.error(e.getMessage(), "/nota/c");
		}
		return "redirect:/nota/r";
	}

}
