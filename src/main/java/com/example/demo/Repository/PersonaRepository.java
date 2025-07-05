package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Models.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

}
