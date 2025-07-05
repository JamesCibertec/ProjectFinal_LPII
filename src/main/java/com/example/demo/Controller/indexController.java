package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/registro")
	public String registro() {
		return "registro";
	}
	
	@GetMapping("/cliente/reserva")
	public String clienteReserva() {
		return "/cliente/reserva";
	}



}
