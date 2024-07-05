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

	@GetMapping(path = "/{id}")
	public Estudiante getEstudiante(@PathVariable Integer id) {
		return this.estudianteService.search(id);
	}
	@GetMapping(path = "/mix/{id}")
	public Estudiante getByPathVariableAndRequestParam(@PathVariable Integer id, @RequestParam String nombre) {
		System.out.println(nombre);
		return this.estudianteService.search(id);
	}
	/*searchByGender?genero=M&edad=35 */
	@GetMapping(path = "/gender")
	//http://localhost:8080/API/v1.0/Matricula/estudiantes/gender?genero=M
	public List<Estudiante> getEstudiantebyGender(@RequestParam String genero) {
		return this.estudianteService.searchByGender(genero);
	}
	//

	@PostMapping
	public void saveEstudiante(@RequestBody Estudiante estu) {
		this.estudianteService.save(estu);
	}

	@PatchMapping(path = "/{id}")
	public void partialUpdateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
		Estudiante aux = this.getEstudiante(id);
		aux.setApellido(estu.getApellido());
		this.estudianteService.update(aux);
		
	}
	//¡Se ha generado el archivo!

	@PutMapping(path = "/{id}")
	public void updateEstudiante(@RequestBody Estudiante estu,@PathVariable Integer id) {
		estu.setId(id);

		Estudiante estudiante = estu;
		this.estudianteService.update(estudiante);
	}

	@DeleteMapping(path = "/{id}") /* SE PUEDE ENVIAR VARIOS PathVariable */
	public void deleteEstudiante(@PathVariable Integer id) {
		this.estudianteService.delete(id);
	}


}
