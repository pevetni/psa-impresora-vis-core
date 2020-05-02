package com.psa.backend.controllers;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.psa.backend.models.Vis;
import com.psa.backend.services.IVisService;

@Controller("vis")
public class VisController {

	@Autowired
	private IVisService visService;

	@Autowired
	RestTemplate restTemplate;

	/* Crear un Vis */
	@PostMapping(value = "/createVis", consumes = "application/json", produces = "application/json")
	public Vis createVis(@RequestBody Vis vis) {

		Vis visCreated = visService.createVis(vis);

		return visCreated;

	}

	/* Listar todos Vis */
	@GetMapping("/listar")
	public @ResponseBody Iterable<Vis> listar() {

		return visService.findAll();
	}

	/* Ver Detalle de un Vis */
	@GetMapping("/detalle/{id}")
	public Vis detalle(@PathVariable Long id) {
		Vis visResponse = visService.findById(id);
		try {
			this.imprimir(visResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return visResponse;
	}

	/* Ver Detalle de un Vis por Serial */
	@GetMapping("/detalleSerial/{serial}")
	public Vis detalleSerial(@PathVariable String serial) {
		return visService.findBySerial(serial);
	}

	/* Listar por Rango de Fechas */
	@GetMapping("/listarByFechas/{desde}/{hasta}")
	public Iterable<Vis> listarByFechas(
			@PathVariable(name = "desde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date desde,
			@PathVariable(name = "hasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hasta) {
		return visService.findByCreatedDateBetween(desde, hasta);
	}

	@GetMapping("/buscarImpresora/{nombre}")
	public PrintService buscarImpresora(@PathVariable String nombre) {

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

	public void imprimir(Vis vis) throws Exception {

		// 1. Create document
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		// 2. Create PdfWriter
		PdfWriter.getInstance(document, new FileOutputStream("/home/juan/Escritorio/test.pdf"));

		// 3. Open document
		document.open();

		// 4. Add content
		document.add(new Paragraph("Consulta VIS. Serial: " + vis.getSerial()));

		// 5. Close document
		document.close();

		File file = new File("/home/juan/Escritorio/test.pdf");

		PDDocument doc = PDDocument.load(file);

		// takes standard printer defined by OS
		PrintService myPrintService = PrintServiceLookup.lookupDefaultPrintService();

		PrinterJob job = PrinterJob.getPrinterJob();

		if (job.printDialog() == true) {
			job.setPrintService(myPrintService);
			job.setPageable(new PDFPageable(doc));
			job.print();
		}

	}

}
