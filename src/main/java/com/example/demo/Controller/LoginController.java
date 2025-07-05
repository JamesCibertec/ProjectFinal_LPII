package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.Models.*;

import com.example.demo.Service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller

public class LoginController {
	@Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String nombreusuario,
                              @RequestParam String contrasena,
                              HttpSession session,
                              Model model) {
        Usuarios usuario = authService.login(nombreusuario, contrasena);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            
            return "redirect:/dashboard/index";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
