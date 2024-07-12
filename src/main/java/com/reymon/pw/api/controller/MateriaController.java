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

import com.reymon.pw.api.repository.modelo.Materia;
import com.reymon.pw.api.service.IMateriaService;

@RestController
@RequestMapping(path = "/materias")
public class MateriaController {

	@Autowired
	private IMateriaService materiaService;
 
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> agregar(@RequestBody Materia materia) {
		this.materiaService.agregar(materia);
		HttpHeaders cabeceraPost= new HttpHeaders();
		cabeceraPost.add("mensaje_201", "Corresponde a la inserción de un recurso");
		cabeceraPost.add("valor", "Materia ingresada con éxito");
		return new ResponseEntity<>(materia ,cabeceraPost,201); 
		//return ResponseEntity.status(201).body(materia);
	}
 
	@PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> modificar(@RequestBody Materia materia, @PathVariable Integer id) {
		materia.setId(id);
		this.materiaService.modificar(materia);

        HttpHeaders cabeceraPut= new HttpHeaders();
		cabeceraPut.add("mensaje_238", "Corresponde a la actualización de un recurso");
		cabeceraPut.add("valor", "Materia actualizado");
		return new ResponseEntity<>(materia,cabeceraPut,238);

		//return ResponseEntity.status(238).body(materia);
	}
 
	@DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.materiaService.borrar(id);
		//return ResponseEntity.status(240).body("Borrada exitosamente");
		HttpHeaders cabeceraDelete= new  HttpHeaders();
		cabeceraDelete.add("mensaje_240", "Corresponde a la eliminación del recurso");
		cabeceraDelete.add("valor", "MAteria eliminada");
		return new ResponseEntity<>("Eliminado correctamente",cabeceraDelete,240);
	}
	@GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> encontrar(@PathVariable Integer id) {

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Materia escontrada");
		return new ResponseEntity<>( this.materiaService.buscar(id),cabeceras,236);
	}
 
	@GetMapping(path = "/credito")
	public List<Materia> buscarPorCredito(@RequestParam Integer credito) {
		List<Materia> lista = this.materiaService.buscarPorCredito(credito);
		return lista;
	}
 
	@GetMapping(path = "/mixto/{id}")
	public Materia buscarMixto(@PathVariable Integer id) {
		return this.materiaService.buscar(id);
	}
 
	@PatchMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materia> actualizarParcial(@RequestBody Materia m, @PathVariable Integer id) {
		m.setId(id);
		Materia materia = this.materiaService.buscar(m.getId());
		if (m.getNombre() != null) {
			materia.setNombre(m.getNombre());
		}
		if (m.getCreditos() != null) {
			materia.setCreditos(m.getCreditos());
		}
		if (m.getProfesor() != null) {
			materia.setProfesor(m.getProfesor());
		}
		if (m.getSemestre() != null) {
			materia.setSemestre(m.getSemestre());
		}
		this.materiaService.modificar(materia);
		//return ResponseEntity.status(239).body(materia);
		HttpHeaders cabeceraPatch= new HttpHeaders();
		cabeceraPatch.add("mensaje_239", "Corresponde a la actualización parcial de un recurso");
		cabeceraPatch.add("valor", "Materia actualizado parcialmente");
		//return ResponseEntity.status(239).body(est2);
		return new ResponseEntity<>(materia,cabeceraPatch,239);
	}
}