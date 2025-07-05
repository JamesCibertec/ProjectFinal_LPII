package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>{
	
	List<Usuarios> findByEstadoTrue();
	Optional<Usuarios> findByNombreusuarioAndContrasena(String nombreusuario, String contrasena);
	
}
