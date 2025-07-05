package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Models.Usuarios;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
	 @GetMapping("/dashboard/index")
	 public String dashboard(HttpSession session, Model model) {
	        Usuarios usuario = (Usuarios) session.getAttribute("usuario");

	        if (usuario == null) {
	            return "redirect:/login";
	        }
	        
	        session.setAttribute("usuario", usuario);
	        return "/dashboard/index";
	    }
	 

}


