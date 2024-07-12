package com.reymon.pw.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

	@GetMapping(path = "/{id}",produces="application/xml")
	public ResponseEntity<Estudiante> getEstudiante(@PathVariable Integer id) {
		this.estudianteService.search(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("hola", "quiubo, estás consultando el resource estudiante");
		headers.add("msg237", "Resource sent");
		// return ResponseEntity.status(236).body(this.estudianteService.search(id));
		return new ResponseEntity<>(this.estudianteService.search(id), headers, 237);

	}

	@GetMapping(path = "/mix/{id}")
	public Estudiante getByPathVariableAndRequestParam(@PathVariable Integer id, @RequestParam String nombre) {
		System.out.println(nombre);
		return this.estudianteService.search(id);
		
	}

	/* searchByGender?genero=M&edad=35 */
	@GetMapping(path = "/gender")
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/gender?genero=M
	public ResponseEntity<List<Estudiante>> getEstudiantebyGender(@RequestParam String genero) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("hola", "quiubo, estás consultando el resource estudiante");
		headers.add("msg240", "Resource sent");
	
		return new ResponseEntity<>(this.estudianteService.searchByGender(genero), headers, 240);

	}
	//

	@PostMapping(produces = "application/json", consumes = "application/xml")
	public ResponseEntity<Estudiante> save(@RequestBody Estudiante estu) {
		this.estudianteService.save(estu);
		HttpHeaders headers = new HttpHeaders();
		headers.add("msg238", "Resource post");
	
		return new ResponseEntity<>(estu, headers, 238);
	}

	@PatchMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> partialUpdateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
		Estudiante aux = this.estudianteService.search(id);
		aux.setApellido(estu.getApellido());
		this.estudianteService.update(aux);
		//return ResponseEntity.status(239).body(aux);
		HttpHeaders headers = new HttpHeaders();
		headers.add("msg238", "Resource updated!");
	
		return new ResponseEntity<>(aux, headers, 238);

	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
		estu.setId(id);

		Estudiante estudiante = estu;
		this.estudianteService.update(estudiante);
		//return ResponseEntity.status(238).body(estudiante);
		HttpHeaders headers = new HttpHeaders();
		headers.add("msg241", "Resource complete updated!");
	
		return new ResponseEntity<>(estu, headers, 241);
	}

	@DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE) /* SE PUEDE ENVIAR VARIOS PathVariable */
	public ResponseEntity<String> deleteEstudiante(@PathVariable Integer id) {
		this.estudianteService.delete(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("msg242", "Resource deleted");
	
		return new ResponseEntity<>("Eliminación estudiante", headers, 238);
		//return ResponseEntity.status(240).body("Borrada exitosamente");
	}

}
