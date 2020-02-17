package com.psa.backend.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.psa.backend.models.Vis;
import com.psa.backend.repository.VisRepository;
import com.psa.backend.services.IVisService;

@Service
public class VisServiceImpl implements IVisService{

	@Autowired
	private VisRepository visRepository;
	
	@Override
	public Vis createVis(Vis vis) {
		return visRepository.save(vis);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Vis> findAll() {
		return (List<Vis>) visRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Vis findById(Long id) {
		return visRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Vis findBySerial(String serial) {
		return visRepository.findBySerial(serial);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Vis> findByCreatedDateBetween(Date desde, Date hasta) {
		return (List<Vis>) visRepository.getAllBetweenDates(desde, hasta);
	}

}
