package org.agaray.notas.controller;

import org.agaray.notas.exception.DangerException;
import org.agaray.notas.exception.PRG;
import org.agaray.notas.service.AlumnoService;
import org.agaray.notas.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService alumnoService;

	@GetMapping("r")
	public String r(ModelMap m) {
		m.put("alumnos", alumnoService.findAll());
		m.put("view", "alumno/r");
		return "_t/frame";
	}

	@GetMapping("c")
	public String c(ModelMap m) {
		m.put("view", "alumno/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido", required = false) String apellido
			) throws DangerException {
		try {
			if (nombre == null || nombre.equals("") ||apellido== null || apellido.equals("") ) {
				throw new Exception("El nombre o apellido no pueden ser nulos");
			}
			alumnoService.save(nombre,apellido);

		} catch (Exception e) {
			PRG.error(e.getMessage(), "/alumno/c");
		}
		return "redirect:/alumno/r";
	}

}
