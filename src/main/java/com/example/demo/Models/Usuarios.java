package com.example.demo.Models;

import jakarta.persistence.*;
import com.example.demo.Models.*;

@Entity
@Table(name="usuario")
public class Usuarios extends Persona  {
	@Column(name = "nombre_usuario", unique = true)
    private String nombreusuario;
	@Column(name = "contrasena")
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private rol rol;

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombre_usuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public rol getRol() {
		return rol;
	}

	public void setRol(rol rol) {
		this.rol = rol;
	}




    
}
