package com.psa.backend.controllers;

import java.awt.print.PrinterJob;
import java.util.Date;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.psa.backend.models.Vis;
import com.psa.backend.services.IVisService;

@RestController("vis")
public class VisController {

	@Autowired
	private IVisService visService;

	@Autowired
	RestTemplate restTemplate;

	/* Crear un Vis */
	@PostMapping(value = "/createVis", consumes = "application/json", produces="application/json")
	public Vis createVis(@RequestBody Vis vis) {

		Vis visCreated = visService.createVis(vis);

		return visCreated;

	}

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
	public List<Vis> listarByFechas(@PathVariable(name = "desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde, @PathVariable(name = "hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
		return visService.findByCreatedDateBetween(desde, hasta);
	}

	@GetMapping("/buscarImpresora/{nombre}")
	public PrintService buscarImpresora(@PathVariable  String nombre) {

		// Obtenemos los servicios de impresion del sistema
		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

		// Recorremos el array de servicios de impresion
		for (PrintService impresora : printServices) {

			// Si el nombre del servicio es el mismo que el que buscamos
			if (impresora.getName().contentEquals(nombre)) {
				return impresora; // Nos devuelve el servicio
			}
		}
		return null; // Si no lo encuentra nos devuelve un null
	}
	
}
