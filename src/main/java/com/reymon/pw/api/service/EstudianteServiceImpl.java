package com.reymon.pw.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reymon.pw.api.repository.IEstudianteRepository;
import com.reymon.pw.api.repository.modelo.Estudiante;
import com.reymon.pw.api.service.to.EstudianteTO;

@Service
public class EstudianteServiceImpl implements IEstudianteService {

	@Autowired
	private IEstudianteRepository estudianteRepository;

	@Autowired
	private IMateriaService materiaService;

	@Override
	public Estudiante search(Integer id) {
		return estudianteRepository.select(id);
	}

	@Override
	public void update(Estudiante estudiante) {
		this.estudianteRepository.update(estudiante);
	}

	@Override
	public void delete(Integer id) {
		this.estudianteRepository.delete(id);
	}

	@Override
	public void save(EstudianteTO estudiante) {
		this.estudianteRepository.insert(this.convertirTO(estudiante));
	}

	@Override
	public List<Estudiante> searchByGender(String genero) {
		// TODO Auto-generated method stub
		List<Estudiante> list = this.estudianteRepository.selectEstudiantesByGender(genero);
		return list;
	}

	public EstudianteTO convertir(Estudiante estu) {
		EstudianteTO esTo = new EstudianteTO();
		esTo.setId(estu.getId());
		esTo.setNombre(estu.getNombre());
		esTo.setApellido(estu.getApellido());
		esTo.setGenero(estu.getGenero());
		esTo.setFechaNacimiento(estu.getFechaNacimiento());
		return esTo;

	}

	public Estudiante convertirTO(EstudianteTO estu) {
		Estudiante esTo = new Estudiante();
		esTo.setId(estu.getId());
		esTo.setNombre(estu.getNombre());
		esTo.setApellido(estu.getApellido());
		esTo.setGenero(estu.getGenero());
		esTo.setFechaNacimiento(estu.getFechaNacimiento());
		return esTo;

	}

	@Override
	public EstudianteTO buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		Estudiante estu = this.estudianteRepository.select(id);
		return this.convertir(estu);
	}

}