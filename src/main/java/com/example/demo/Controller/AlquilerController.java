package com.example.demo.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.example.demo.Repository.ClienteRepository;
import com.example.demo.Models.Canchas;
import com.example.demo.Models.Cliente;
import com.example.demo.Models.ReservaAlquiler;
import com.example.demo.Models.TipoPago;
import com.example.demo.Models.Usuarios;
import com.example.demo.Repository.CanchaRepository;
import com.example.demo.Repository.TipoPagoRepository;
import com.example.demo.Repository.AlquilerRepository;

@Controller
@RequestMapping("/dashboard")
public class AlquilerController {
	
	@Autowired
	private AlquilerRepository alquilerRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CanchaRepository canchaRepository;
	
	@Autowired
	private TipoPagoRepository tipoPagoRepository;
		
	@GetMapping("/alquiler/create")
	public String mostrarFormulario(Model model) {
		
		model.addAttribute("alquiler", new ReservaAlquiler());		
		
		List<Cliente> clientes  = clienteRepository.findByEstadoTrue();
		model.addAttribute("clientes", clientes );
		List<Canchas> canchas  = canchaRepository.findByEstadoTrue();
		model.addAttribute("canchas", canchas );
		List<TipoPago> tipoPago  = tipoPagoRepository.findByEstadoTrue();
		model.addAttribute("tipoPago", tipoPago );
		return "/dashboard/alquiler/create";
	}
	
	@PostMapping("/alquiler/save")
	public String guardarDocumento(@ModelAttribute ReservaAlquiler alquiler) {
		alquilerRepository.save(alquiler);
		return "redirect:/dashboard/consultar/reserva-alquiler";
	}
	
	
	@GetMapping("/alquiler/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		ReservaAlquiler alquiler = alquilerRepository.findById(id).orElseThrow();
		List<Cliente> clientes  = clienteRepository.findByEstadoTrue();
		model.addAttribute("clientes", clientes );
		List<Canchas> canchas  = canchaRepository.findByEstadoTrue();
		model.addAttribute("canchas", canchas );
		List<TipoPago> tipoPago  = tipoPagoRepository.findByEstadoTrue();
		model.addAttribute("tipoPago", tipoPago );
		model.addAttribute("alquiler", alquiler);
		return "/dashboard/alquiler/edit";
	}
	
	@GetMapping("/alquiler/confirm/{id}")
	public String ConfirmarAlquiler(@PathVariable Long id, Model model) {
		ReservaAlquiler alquiler = alquilerRepository.findById(id).orElseThrow();
		List<Cliente> clientes  = clienteRepository.findByEstadoTrue();
		model.addAttribute("clientes", clientes );
		List<Canchas> canchas  = canchaRepository.findByEstadoTrue();
		model.addAttribute("canchas", canchas );
		List<TipoPago> tipoPago  = tipoPagoRepository.findByEstadoTrue();
		model.addAttribute("tipoPago", tipoPago );
		model.addAttribute("alquiler", alquiler);
		return "/dashboard/alquiler/confirm";
	}

	
	
}
