package com.reymon.pw.api.service;

import java.util.List;

import com.reymon.pw.api.repository.modelo.Estudiante;
import com.reymon.pw.api.service.to.EstudianteTO;

public interface IEstudianteService {

    // CRUD
    public Estudiante search(Integer id);

    public EstudianteTO buscarPorId(Integer id);
    public void update(Estudiante estudiante);

    public void delete(Integer id);

    public void save(EstudianteTO estudiante);

    public List<Estudiante> searchByGender(String genero);
    public List<EstudianteTO> selectAll();
}
