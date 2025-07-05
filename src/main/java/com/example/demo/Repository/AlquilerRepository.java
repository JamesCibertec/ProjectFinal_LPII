package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.ReservaAlquiler;

public interface AlquilerRepository extends JpaRepository<ReservaAlquiler, Long> {

}
