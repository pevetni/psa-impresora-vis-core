package com.psa.backend.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.psa.backend.models.Vis;
import com.psa.backend.services.IVisService;

@RestController("vis")
public class VisController {

	@Autowired
	private IVisService visService;

//	@Autowired
//	RestTemplate restTemplate;
//
//	/* Crear un Vis */
//	@GetMapping("/createVis")
//	public RestTemplate createVis(Vis vis) {
//
//		Vis visCreated = visService.createVis(vis);
//
//		RestTemplate plantilla = new RestTemplate();
//
//		// Ruta en Proyecto Front
//		plantilla.getForObject("http://localhost:4200/newVis", String.class);
//
//		return plantilla;
//
//	}

	/* Listar todos Vis */
	@GetMapping("/listar")
	public List<Vis> listar() {

		return visService.findAll();
	}

	/* Ver Detalle de un Vis */
	@GetMapping("/detalle/{id}")
	public Vis detalle(@PathVariable Long id) {
		return visService.findById(id);
	}

	/* Listar por Rango de Fechas */
	@GetMapping("/listarByFechas/{desde}/{hasta}")
	public List<Vis> listarByFechas(@PathVariable Date desde, @PathVariable Date hasta) {
		return visService.findByCreatedDateBetween(desde, hasta);
	}

}
