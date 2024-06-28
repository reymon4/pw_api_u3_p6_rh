package com.reymon.pw.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reymon.pw.api.repository.modelo.Estudiante;
import com.reymon.pw.api.service.IEstudianteService;

@RestController
@RequestMapping(path = "/estudiantes")

public class EstudianteController {
	@Autowired
	private IEstudianteService estudianteService;

	@GetMapping(path = "/search")
	public Estudiante getEstudiante() {
		return this.estudianteService.search(1);
	}

	@PostMapping(path = "/save")
	public void saveEstudiante(@RequestBody Estudiante estu) {
		this.estudianteService.save(estu);
	}

	@PatchMapping(path = "/partialUpdate")
	public void partialUpdateEstudiante() {
		Estudiante estudiante = this.estudianteService.search(1);

		estudiante.setNombre("Ola");
		this.estudianteService.update(estudiante);
	}

	@PutMapping(path = "/update")
	public void updateEstudiante() {
		Estudiante estudiante = this.estudianteService.search(1);
		estudiante.setFechaNacimiento(LocalDateTime.of(2005, 10, 4, 5, 34, 1));
		estudiante.setNombre("KING");
		estudiante.setApellido("KONG");
		this.estudianteService.update(estudiante);
	}

	@DeleteMapping(path = "/delete")
	public void deleteEstudiante() {
		this.estudianteService.delete(1);
	}

}
