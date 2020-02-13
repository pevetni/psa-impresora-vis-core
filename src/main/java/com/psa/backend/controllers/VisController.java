package com.psa.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.psa.backend.models.Vis;
import com.psa.backend.services.VisService;

@RestController("vis")
public class VisController {
	
	@Autowired
	private VisService visService;
	
	public RestTemplate createVis(Vis vis) {
		
		Vis visCreated = visService.createVis(vis);
		
		return null;
	}
}
