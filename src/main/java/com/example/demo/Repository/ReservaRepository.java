package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.ReservaAlquiler;

public interface ReservaRepository extends JpaRepository<ReservaAlquiler, Long> {

}
