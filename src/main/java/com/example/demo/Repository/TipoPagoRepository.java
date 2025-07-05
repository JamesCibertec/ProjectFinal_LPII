package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Canchas;
import com.example.demo.Models.TipoPago;

public interface TipoPagoRepository extends JpaRepository<TipoPago, Long> {
	List<TipoPago> findByEstadoTrue();
}
