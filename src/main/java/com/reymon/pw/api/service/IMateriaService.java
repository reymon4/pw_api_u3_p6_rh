package com.reymon.pw.api.service;

import java.util.List;

import com.reymon.pw.api.repository.modelo.Materia;
import com.reymon.pw.api.service.to.MateriaTO;

public interface IMateriaService {

	void agregar(Materia materia);
	void modificar(Materia materia);
	Materia buscar(Integer id);
	void borrar(Integer id);
	List<Materia> buscarPorCredito(Integer credito);
	public List<MateriaTO> buscarPorIdEstudiante(Integer id);

	public MateriaTO convertir(Materia materia);

	public List<MateriaTO> convertirLista(List<Materia> materia);
	public List<Materia> convertirListaTO(List<MateriaTO> materia);


}