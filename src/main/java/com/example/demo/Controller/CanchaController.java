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

import com.example.demo.Models.*;
import com.example.demo.Repository.*;

@Controller
@RequestMapping("/dashboard")
public class CanchaController {
	@Autowired
	private CanchaRepository canchaRepository;
	
	@GetMapping("/consultar/list-canchas")
	public String list(Model model) {
		List<Canchas> list = canchaRepository.findAll();
		model.addAttribute("canchas", list);
		return "/dashboard/consultar/list-canchas";
	}
	
	@GetMapping("/canchas/create")
	public String mostrarFormulario(Model model) {
		model.addAttribute("canchas", new Canchas());
		return "/dashboard/canchas/create";
	}
	
	@PostMapping("/canchas/save")
	public String guardarDocumento(@ModelAttribute Canchas canchas) {
		canchaRepository.save(canchas);
		return "redirect:/dashboard/consultar/list-canchas";
	}
	
	
	@GetMapping("/canchas/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		Canchas canchas = canchaRepository.findById(id).orElseThrow();
		model.addAttribute("canchas", canchas);
		return "/dashboard/canchas/edit";
	}

	
	@GetMapping("/canchas/delete/{id}")
	public String inactivarTipoPago(@PathVariable Long id) {
		Canchas canchas = canchaRepository.findById(id).orElseThrow();

	    if (canchas != null) {
	    	canchas.setEstado(false); 
	        canchaRepository.save(canchas);
	    }

	    return "redirect:/dashboard/consultar/list-canchas";
	}
}
