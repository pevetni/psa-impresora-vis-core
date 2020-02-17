package com.psa.backend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.psa.backend.models.Vis;

public interface VisRepository extends CrudRepository<Vis, Long>{
	/************************************************************************************************** 
	 * CrudRepository ya tiene metodos genericos para poder Agregar, Eliminar, Consultar uno o varios *
	 * ************************************************************************************************/
	
	
    @Query(value = "FROM Vis v WHERE v.createAt BETWEEN :desde AND :hasta")
    public List<Vis> getAllBetweenDates(@Param("desde")Date desde,@Param("hasta")Date hasta);
    
    @Query(value = "FROM Vis v WHERE v.serial =:serial")
    public Vis findBySerial(@Param("serial") String serial);
}
