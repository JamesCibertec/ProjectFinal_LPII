package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.ReservaAlquiler;

public interface ConsultaReservaAlquilerRepository extends JpaRepository<ReservaAlquiler, Long> {
	List<ReservaAlquiler> findByFecha(LocalDate fecha);
    List<ReservaAlquiler> findByEstado(String estado);
    List<ReservaAlquiler> findByFechaAndEstado(LocalDate fecha, String estado);
    
    Optional<ReservaAlquiler> findById(Long id);
}
