package com.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Models.ReservaAlquiler;
import com.example.demo.Repository.ConsultaReservaAlquilerRepository;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

	    @Autowired 
		private ConsultaReservaAlquilerRepository consultaReservaAlquilerRepository;
		
		@GetMapping("/reserva/{id}")
		public ResponseEntity<byte[]> generarReportePorId(@PathVariable Long id) throws Exception {
		    InputStream jasperStream = 
		    		this.getClass().getResourceAsStream("/reportes/reserva_detalle.jasper");

		    Map<String, Object> params = new HashMap<>();
		    params.put("empresa", "FredyQueenSoccer");
		    
		    ReservaAlquiler reserva = consultaReservaAlquilerRepository.findById(id).orElseThrow();
		    List<ReservaAlquiler> lisProductos = List.of(reserva);
		    
		    JRBeanCollectionDataSource dataSource = 
		    		new JRBeanCollectionDataSource(lisProductos);

		    JasperReport jasperReport = (JasperReport) 
		    		JRLoader.loadObject(jasperStream);
		    JasperPrint jasperPrint = 
		    		JasperFillManager.fillReport(jasperReport, params, dataSource);

		    byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		    
		    String estado = reserva.getEstado(); // o dto.getEstado()
		

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_PDF);
		    headers.setContentDispositionFormData("filename", "Constancia_"+ estado+"_Codigo "+ id +".pdf");

		    return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
		}
		
}
