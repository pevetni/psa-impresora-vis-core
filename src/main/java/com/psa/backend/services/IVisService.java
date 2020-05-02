package com.psa.backend.services;

import java.util.Date;
import java.util.List;

import com.psa.backend.models.Vis;


public interface IVisService {
	
	public Vis createVis(Vis vis);
	
	public Vis findById(Long id);
	
	public Vis findBySerial(String serial);
	
	public Iterable<Vis> findAll();
	
	public Iterable<Vis> findByCreatedDateBetween(Date desde, Date hasta);
}
