package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	 List<Cliente> findByEstadoTrue();
}
