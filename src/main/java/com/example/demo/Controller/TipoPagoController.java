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

import com.example.demo.Models.TipoPago;
import com.example.demo.Repository.TipoPagoRepository;

@Controller
@RequestMapping("/dashboard")
public class TipoPagoController {
	@Autowired
	private TipoPagoRepository tipoPagoRepository;
	
	@GetMapping("/consultar/list-tipopago")
	public String list(Model model) {
		List<TipoPago> list = tipoPagoRepository.findAll();
		model.addAttribute("tipoPagos", list);
		return "/dashboard/consultar/list-tipopago";
	}
	
	@GetMapping("/tipoPago/create")
	public String mostrarFormulario(Model model) {
		model.addAttribute("tipoPago", new TipoPago());
		return "/dashboard/tipoPago/create";
	}
	
	@PostMapping("/tipoPago/save")
	public String guardarDocumento(@ModelAttribute TipoPago tipoPago) {
		tipoPagoRepository.save(tipoPago);
		return "redirect:/dashboard/consultar/list-tipopago";
	}
	
	
	@GetMapping("/tipoPago/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		TipoPago tipoPago = tipoPagoRepository.findById(id).orElseThrow();
		model.addAttribute("tipoPago", tipoPago);
		return "/dashboard/tipoPago/edit";
	}
	/*
	@GetMapping("/tipoPago/delete/{id}")
	public String guardarTipoPago(@PathVariable Long id) {
		tipoPagoRepository.deleteById(id);
		return "redirect:/dashboard/consultar/list-tipopago";
	}*/
	
	@GetMapping("/tipoPago/delete/{id}")
	public String inactivarTipoPago(@PathVariable Long id) {
	    TipoPago tipoPago = tipoPagoRepository.findById(id).orElseThrow();

	    if (tipoPago != null) {
	        tipoPago.setEstado(false); 
	        tipoPagoRepository.save(tipoPago);
	    }

	    return "redirect:/dashboard/consultar/list-tipopago";
	}
	
	
	
}
