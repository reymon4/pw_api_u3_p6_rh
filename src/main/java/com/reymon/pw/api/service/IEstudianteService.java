package com.reymon.pw.api.service;

import com.reymon.pw.api.repository.modelo.Estudiante;

public interface IEstudianteService {

    // CRUD
    public Estudiante search(Integer id);

    public void update(Estudiante estudiante);

    public void delete(Integer id);

    public void save(Estudiante estudiante);

}
