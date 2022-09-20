package org.agaray.notas.controller;

import org.agaray.notas.exception.DangerException;
import org.agaray.notas.exception.PRG;
import org.agaray.notas.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/asignatura")
public class AsignaturaController {

	@Autowired
	private AsignaturaService asignaturaService;

	@GetMapping("r")
	public String r(ModelMap m) {
		m.put("asignaturas", asignaturaService.findAll());
		m.put("view", "asignatura/r");
		return "_t/frame";
	}

	@GetMapping("c")
	public String c(ModelMap m) {
		m.put("view", "asignatura/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String cPost(@RequestParam(value = "nombre", required = false) String nombre) throws DangerException {
		try {
			if (nombre == null || nombre.equals("")) {
				throw new Exception("El nombre no puede ser vacío");
			}
			asignaturaService.save(nombre);

		} catch (Exception e) {
			PRG.error(e.getMessage(), "/asignatura/c");
		}
		return "redirect:/asignatura/r";
	}

}
