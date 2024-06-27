package com.reymon.pw.api.repository;

import com.reymon.pw.api.repository.modelo.Estudiante;

public interface IEstudianteRepository {
    //CRUD
    public Estudiante select(Integer id); 
    public void update(Estudiante estudiante);
    public void delete(Integer id);
    public void insert(Estudiante estudiante);
}
