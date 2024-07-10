package com.reymon.pw.api.repository;

import java.util.List;

import com.reymon.pw.api.repository.modelo.Materia;

public interface IMateriaRepository {
    void insertar(Materia materia);
	void actualizar(Materia materia);
	Materia seleccionar(Integer id);
	void eliminar(Integer id);
	List<Materia> buscarPorCredito(Integer credito);

}