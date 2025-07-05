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

import com.example.demo.Models.Cliente;
import com.example.demo.Repository.ClienteRepository;

@Controller
@RequestMapping("/dashboard")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/consultar/list-clientes")
	public String list(Model model) {
		List<Cliente> list = clienteRepository.findAll();
		model.addAttribute("clientes", list);
		return "/dashboard/consultar/list-clientes";
	}
	
	@GetMapping("/clientes/create")
	public String mostrarFormulario(Model model) {
		model.addAttribute("clientes", new Cliente());
		return "/dashboard/clientes/create";
	}
	
	@PostMapping("/clientes/save")
	public String guardarDocumento(@ModelAttribute Cliente clientes) {
		clienteRepository.save(clientes);
		return "redirect:/dashboard/consultar/list-clientes";
	}
	
	
	@GetMapping("/clientes/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		Cliente clientes = clienteRepository.findById(id).orElseThrow();
		model.addAttribute("clientes", clientes);
		return "/dashboard/clientes/edit";
	}

	
	@GetMapping("/clientes/delete/{id}")
	public String inactivarTipoPago(@PathVariable Long id) {
		Cliente clientes = clienteRepository.findById(id).orElseThrow();

	    if (clientes != null) {
	    	clientes.setEstado(false); 
	        clienteRepository.save(clientes);
	    }

	    return "redirect:/dashboard/consultar/list-clientes";
	}
}
