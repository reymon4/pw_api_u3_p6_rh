package com.reymon.pw.api.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reymon.pw.api.repository.modelo.Estudiante;
import com.reymon.pw.api.service.IEstudianteService;
import com.reymon.pw.api.service.IMateriaService;
import com.reymon.pw.api.service.to.EstudianteTO;
import com.reymon.pw.api.service.to.MateriaTO;




@RestController
@CrossOrigin
@RequestMapping(path = "/estudiantes")

public class EstudianteController {
	@Autowired
	private IEstudianteService estudianteService;

	@Autowired
	private IMateriaService iMateriaService;

//	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Estudiante> getEstudiante(@PathVariable Integer id) {
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("hola", "quiubo, estás consultando el resource estudiante");
//		headers.add("msg237", "Resource sent");
//		// return ResponseEntity.status(236).body(this.estudianteService.search(id));
//		return new ResponseEntity<>(this.estudianteService.search(id), headers, 237);
//
//	}

	@GetMapping(path = "/mix/{id}")
	public Estudiante getByPathVariableAndRequestParam(@PathVariable Integer id, @RequestParam String nombre) {
		System.out.println(nombre);
		return this.estudianteService.search(id);
	}

	/* searchByGender?genero=M&edad=35 */
	@GetMapping(path = "/gender")
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/gender?genero=M
	public List<Estudiante> getEstudiantebyGender(@RequestParam String genero) {
		return this.estudianteService.searchByGender(genero);
	}
	//

	@PostMapping
	public ResponseEntity<EstudianteTO> save(@RequestBody EstudianteTO estu) {
	System.out.println(estu.toString());
		this.estudianteService.save(estu);
		return ResponseEntity.status(201).body(estu);

	}

//	@PatchMapping(path = "/{id}")
//	public ResponseEntity<Estudiante> partialUpdateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
//		Estudiante aux = this.estudianteService.search(id);
//		aux.setApellido(estu.getApellido());
//		this.estudianteService.update(aux);
//		return ResponseEntity.status(239).body(aux);
//
//	}

//	@PutMapping(path = "/{id}")
//	public ResponseEntity<Estudiante> updateEstudiante(@RequestBody Estudiante estu, @PathVariable Integer id) {
//		estu.setId(id);
//
//		Estudiante estudiante = estu;
//		this.estudianteService.update(estudiante);
//		return ResponseEntity.status(238).body(estudiante);
//	}

//	@DeleteMapping(path = "/{id}") /* SE PUEDE ENVIAR VARIOS PathVariable */
//	public ResponseEntity<String> deleteEstudiante(@PathVariable Integer id) {
//		this.estudianteService.delete(id);
//		return ResponseEntity.status(240).body("Borrada exitosamente");
//	}

//	@GetMapping(path = "/hateoas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public EstudianteTO buscarHateoas(@PathVariable Integer id) {
//		EstudianteTO estudiante = this.estudianteService.buscarPorId(id);
//		// ERROR es una carga EAGER
//		/*
//		 * List<MateriaTO> lista= this.iMateriaService.buscarPorIdEstudiante(id);
//		 * estudiante.setMaterias(lista);
//		 */
//
//		// PARA CREAR EL HIPERVINCULO USAMOS LA CLASE LINK
//		Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriasPorIdEStudiante(id))
//				.withRel("sus materias");
//		Link myLink2 = linkTo(methodOn(EstudianteController.class).getEstudiante(id)).withRel("sus materias");
//
//		estudiante.add(myLink);
//		estudiante.add(myLink2);
//
//		return estudiante;
//
//	}

	@GetMapping(path = "{id}/materiassss", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<MateriaTO> buscarMateriasPorIdEStudiante(@PathVariable Integer id) {
		return this.iMateriaService.buscarPorIdEstudiante(id);
	}

	// TAREA 9
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EstudianteTO> buscarHateoas() {
		List<EstudianteTO> estudiantes = this.estudianteService.selectAll();

		for (EstudianteTO est : estudiantes) {
			Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriasPorIdEStudiante(est.getId()))
					.withRel("Materias del estudiante");
			est.add(myLink);
		}

		return estudiantes;

	}
	
	//Taller33
	@DeleteMapping(path = "/{cedula}") /* SE PUEDE ENVIAR VARIOS PathVariable */
	public ResponseEntity<String> deleteEstudiantebyCedula(@PathVariable String cedula) {
		this.estudianteService.deleteByCedula(cedula);
		return ResponseEntity.status(240).body("Borrada exitosamente");
	}
	@GetMapping(path = "/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> getEstudianteByCedula(@PathVariable String cedula) {
			return ResponseEntity.status(200).body(this.estudianteService.searchByCedula(cedula));

	}
	@PutMapping(path = "/{cedula}", consumes= MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EstudianteTO> updateEstudianteByCedula(@RequestBody EstudianteTO estu, @PathVariable String cedula) {
		
		EstudianteTO estudiante = this.estudianteService.searchByCedula(cedula);
		estu.setId(estudiante.getId());
		this.estudianteService.update(estu);
		return ResponseEntity.status(HttpStatus.OK).body(estudiante);
	}
	

}
