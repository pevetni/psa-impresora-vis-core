package com.psa.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.psa.backend.models.Vis;

public interface VisRepository extends CrudRepository<Vis, Long>{
	/************************************************************************************************** 
	 * CrudRepository ya tiene metodos genericos para poder Agregar, Eliminar, Consultar uno o varios *
	 * ************************************************************************************************/
}
