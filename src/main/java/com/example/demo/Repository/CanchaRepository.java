package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.*;

public interface CanchaRepository extends JpaRepository<Canchas, Long>{
	List<Canchas> findByEstadoTrue();
}
