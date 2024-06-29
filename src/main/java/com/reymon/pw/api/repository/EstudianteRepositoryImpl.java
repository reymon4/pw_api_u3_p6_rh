package com.reymon.pw.api.repository;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.reymon.pw.api.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    @Override
    public List<Estudiante> selectEstudiantesByGender(String genero ) {
        // TODO Auto-generated method stub
        TypedQuery<Estudiante> query = this.em.createQuery("SELECT e FROM Estudiante e WHERE e.genero = genero", Estudiante.class);
        query.setParameter(genero, "genero");
        return query.getResultList();
    }

  
}