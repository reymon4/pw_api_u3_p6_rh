package com.reymon.pw.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reymon.pw.api.repository.modelo.Estudiante;
import com.reymon.pw.api.service.IEstudianteService;

@RestController
@RequestMapping(path = "/estudiantes")

public class EstudianteController {
	@Autowired
	private IEstudianteService estudianteService;

	@GetMapping(path = "/search/{id}")
	public Estudiante getEstudiante(@PathVariable Integer id) {
		return this.estudianteService.search(id);
	}
	@GetMapping(path = "/searchByGender")
	public List<Estudiante> getEstudiantebyGender(@RequestParam String genero) {
		return this.estudianteService.searchByGender(genero);
	}

	@PostMapping(path = "/save")
	public void saveEstudiante(@RequestBody Estudiante estu) {
		this.estudianteService.save(estu);
	}

	@PatchMapping(path = "/partialUpdate")
	public void partialUpdateEstudiante(@RequestBody Estudiante estu) {
		this.estudianteService.update(estu);
	}

	@PutMapping(path = "/update")
	public void updateEstudiante(@RequestBody Estudiante estu) {
		Estudiante aux = this.estudianteService.search(estu.getId());
		/*Only Lastname update */
		aux.setApellido(estu.getApellido());
		this.estudianteService.update(aux);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void deleteEstudiante(@PathVariable Integer id) {
		this.estudianteService.delete(id);
	}


}
