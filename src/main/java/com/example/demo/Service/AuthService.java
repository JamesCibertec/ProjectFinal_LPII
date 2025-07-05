package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.UsuariosRepository;
import com.example.demo.Models.*;

@Service
public class AuthService {
	 @Autowired
	    private UsuariosRepository usuarioRepository;

	    public Usuarios login(String nombreusuario, String contrasena) {
	        return usuarioRepository.findByNombreusuarioAndContrasena(nombreusuario, contrasena)
	                .orElse(null);
	    }
}
