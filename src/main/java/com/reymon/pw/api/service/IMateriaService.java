package com.reymon.pw.api.service;

import com.reymon.pw.api.repository.modelo.Materia;

public interface IMateriaService {

	void agregar(Materia materia);
	void modificar(Materia materia);
	Materia buscar(Integer id);
	void borrar(Integer id);

}