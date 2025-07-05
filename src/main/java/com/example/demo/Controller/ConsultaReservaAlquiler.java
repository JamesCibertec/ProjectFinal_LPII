package com.example.demo.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Models.*;
import com.example.demo.Repository.ConsultaReservaAlquilerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/dashboard")
public class ConsultaReservaAlquiler {
	@Autowired
	private ConsultaReservaAlquilerRepository consultaReservaAlquilerRepository;
	
	@GetMapping("/consultar/reserva-alquiler")
	public String list(Model model) {
		List<ReservaAlquiler> list = consultaReservaAlquilerRepository.findAll();
		model.addAttribute("auxs", list);
		return "/dashboard/consultar/reserva-alquiler";
	}
	
	@GetMapping("/consultar/filtrar")
	public String filtrarReservas(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) String estado,
            Model model) {	
		List<ReservaAlquiler> reservas;	
		if (fecha != null && estado != null && !estado.isEmpty()) {
		    reservas = consultaReservaAlquilerRepository.findByFechaAndEstado(fecha, estado);
		} else if (fecha != null) {
		    reservas = consultaReservaAlquilerRepository.findByFecha(fecha);
		} else if (estado != null && !estado.isEmpty()) {
		    reservas = consultaReservaAlquilerRepository.findByEstado(estado);
		} else {
		    reservas = consultaReservaAlquilerRepository.findAll();
		}
		
		BigDecimal total = BigDecimal.ZERO;
		for (ReservaAlquiler r : reservas) {
		    if (r.getMonto() != null) {
		        total = total.add(r.getMonto());
		    }
		}

		model.addAttribute("auxs", reservas);
		model.addAttribute("totalMonto", total);
		return "/dashboard/consultar/reserva-alquiler";
	}
	
	
	@GetMapping("/consultar/eventos")
	@ResponseBody //	JSON	Métodos individuales-  devolver JSON en lugar de una vista HTML.
	public List<Map<String, Object>> listarEventos() {
	    List<ReservaAlquiler> reservas = consultaReservaAlquilerRepository.findAll();
	    List<Map<String, Object>> eventos = new ArrayList<>();

	    for (ReservaAlquiler r : reservas) {
	        Map<String, Object> evento = new HashMap<>();
	        evento.put("id",r.getId_reserva() );
	        evento.put("title", r.getCliente().getNombres()+ " "+ r.getCliente().getApellidos()+" | "+ r.getCanchas().getNombre() + " - " + r.getEstado());
	        evento.put("start", r.getFecha().toString() + "T" + r.getHora_inicio().toString());
	        evento.put("end", r.getFecha().toString() + "T" + r.getHora_fin().toString());
	        evento.put("color", r.getEstado().equals("Alquilado") ? "#28a745" : "#ffc107");
	        
	        //Para el Modal
	        evento.put("cliente", r.getCliente().getNombres()+ " "+ r.getCliente().getApellidos());
	        evento.put("cancha", r.getCanchas().getNombre()+" "+ r.getCanchas().getDescripcion());
	        evento.put("tipo", r.getEstado());
	        evento.put("monto", r.getMonto());
	        eventos.add(evento);
	    }
	    return eventos;
	}
	
}
