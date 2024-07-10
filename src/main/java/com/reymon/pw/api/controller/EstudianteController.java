package com.reymon.pw.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Estudiante>  getEstudiante(@PathVariable Integer id) {
		 this.estudianteService.search(id);
		return ResponseEntity.status(236).body(this.estudianteService.search(id));
		
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
	public ResponseEntity<Estudiante> save(@RequestBody Estudiante estu) {
		this.estudianteService.save(estu);
		return ResponseEntity.status(201).body(estu);
		
	}

	@PatchMapping(path = "/{id}")
	public ResponseEntity<Estudiante> partialUpdateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
		Estudiante aux = this.estudianteService.search(id);
		aux.setApellido(estu.getApellido());
		this.estudianteService.update(aux);
		return ResponseEntity.status(239).body(aux);
		
		
	}


	@PutMapping(path = "/{id}")
	public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estu,@PathVariable Integer id) {
		estu.setId(id);

		Estudiante estudiante = estu;
		this.estudianteService.update(estudiante);
		return ResponseEntity.status(238).body(estudiante);
	}

	@DeleteMapping(path = "/{id}") /* SE PUEDE ENVIAR VARIOS PathVariable */
	public  ResponseEntity<String> deleteEstudiante(@PathVariable Integer id) {
		this.estudianteService.delete(id);
		return ResponseEntity.status(240).body("Borrada exitosamente");
	}


}
