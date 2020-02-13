package com.psa.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.psa.backend.models.Vis;
import com.psa.backend.repository.VisRepository;
import com.psa.backend.services.VisService;

public class VisServiceImpl implements VisService{

	@Autowired
	private VisRepository visRepository;
	
	@Override
	public Vis createVis(Vis vis) {
		return visRepository.save(vis);
	}

}
