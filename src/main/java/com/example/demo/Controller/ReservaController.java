package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.Canchas;
import com.example.demo.Models.Cliente;
import com.example.demo.Models.ReservaAlquiler;
import com.example.demo.Models.TipoPago;
import com.example.demo.Repository.ReservaRepository;
import com.example.demo.Repository.CanchaRepository;
import com.example.demo.Repository.ClienteRepository;



@Controller
@RequestMapping("/dashboard")
public class ReservaController {
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CanchaRepository canchaRepository;
	

		
	@GetMapping("/reserva/create")
	public String mostrarFormulario(Model model) {
		model.addAttribute("reserva", new ReservaAlquiler());		
		
		List<Cliente> clientes  = clienteRepository.findByEstadoTrue();
		model.addAttribute("clientes", clientes );
		List<Canchas> canchas  = canchaRepository.findByEstadoTrue();
		model.addAttribute("canchas", canchas );
		return "/dashboard/reserva/create";
	}
	
	@PostMapping("/reserva/save")
	public String guardarDocumento(@ModelAttribute ReservaAlquiler reserva) {
		reservaRepository.save(reserva);
		return "redirect:/dashboard/consultar/reserva-alquiler";
	}
	
	
	@GetMapping("/reserva/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		ReservaAlquiler reserva = reservaRepository.findById(id).orElseThrow();
		List<Cliente> clientes  = clienteRepository.findByEstadoTrue();
		model.addAttribute("clientes", clientes );
		List<Canchas> canchas  = canchaRepository.findByEstadoTrue();
		model.addAttribute("canchas", canchas );
		model.addAttribute("reserva", reserva);
		return "/dashboard/reserva/edit";
	}

	@GetMapping("/reserva/delete/{id}")
	public String guardarTipoPago(@PathVariable Long id) {
		reservaRepository.deleteById(id);
		return "redirect:/dashboard/consultar/reserva-alquiler";
	}
}
