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


import com.example.demo.Models.Usuarios;
import com.example.demo.Models.rol;
import com.example.demo.Repository.UsuariosRepository;
import com.example.demo.Repository.RolRepository;

@Controller
@RequestMapping("/dashboard")
public class UsuarioController {
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping("/consultar/list-usuario")
	public String list(Model model) {
		List<Usuarios> usuarios  = usuariosRepository.findAll();
		List<Usuarios> usuariosSinCliente = usuarios.stream()
		        .filter(u -> u.getRol() != null) // evita NullPointerException
		        .filter(u -> !u.getRol().getNombre().equalsIgnoreCase("Cliente")) // excluye rol "Cliente"
		        .collect(Collectors.toList());
		model.addAttribute("usuarios", usuariosSinCliente );
		return "/dashboard/consultar/list-usuario";
	}
	
	@GetMapping("/usuarios/create")
	public String mostrarFormulario(Model model) {
		model.addAttribute("usuarios", new Usuarios());
		List<rol> rol  = rolRepository.findAll();
		model.addAttribute("rol", rol );
		return "/dashboard/usuarios/create";
	}
	
	@PostMapping("/usuarios/save")
	public String guardarusuarios(@ModelAttribute Usuarios usuarios) {
		usuariosRepository.save(usuarios);
		return "redirect:/dashboard/consultar/list-usuario";
	}
	
	@GetMapping("/usuarios/edit/{id}")
	public String mostrarEditar(@PathVariable Long id, Model model) {
		Usuarios usuarios = usuariosRepository.findById(id).orElseThrow();
		List<rol> rol  = rolRepository.findAll();
		model.addAttribute("rol", rol );
		model.addAttribute("usuarios", usuarios);
		return "/dashboard/usuarios/edit";
	}

	@GetMapping("/usuarios/delete/{id}")
	public String inactivarusuarios(@PathVariable Long id) {
		Usuarios usuarios = usuariosRepository.findById(id).orElseThrow();

	    if (usuarios != null) {
	    	usuarios.setEstado(false); 
	        usuariosRepository.save(usuarios);
	    }

	    return "redirect:/dashboard/consultar/list-usuario";
	}
}
