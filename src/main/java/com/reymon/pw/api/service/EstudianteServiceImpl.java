package com.reymon.pw.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reymon.pw.api.repository.IEstudianteRepository;
import com.reymon.pw.api.repository.modelo.Estudiante;

@Service
public class EstudianteServiceImpl implements IEstudianteService{

    @Autowired
    private IEstudianteRepository estudianteRepository;
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
    public void save(Estudiante estudiante) {
        this.estudianteRepository.insert(estudiante);
    }

    @Override
    public List<Estudiante> searchByGender(String genero) {
        // TODO Auto-generated method stub
     return this.estudianteRepository.selectEstudiantesByGender(genero);
    }

    
}