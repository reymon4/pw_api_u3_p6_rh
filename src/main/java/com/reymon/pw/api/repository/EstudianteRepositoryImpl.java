package com.reymon.pw.api.repository;



import org.springframework.stereotype.Repository;

import com.reymon.pw.api.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class EstudianteRepositoryImpl implements IEstudianteRepository{

      //CRUD
    @PersistenceContext
    private EntityManager em;
    @Override
    public Estudiante select(Integer id) {
        // TODO Auto-generated method stub
      return this.em.find(Estudiante.class, id);
    }

    @Override
    public void update(Estudiante estudiante) {
        // TODO Auto-generated method stub
        this.em.merge(estudiante);
    }

    @Override
    public void delete(Integer id) {
        // TODO Auto-generated method stub
        this.em.remove(this.select(id));
    }

    @Override
    public void insert(Estudiante estudiante) {
        // TODO Auto-generated method stub
     this.em.persist(estudiante);
    }

  
}