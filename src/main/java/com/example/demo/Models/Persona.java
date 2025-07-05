package com.example.demo.Models;

import jakarta.persistence.*;

@Entity
@Table(name="persona")
@Inheritance(strategy = InheritanceType.JOINED)
//@MappedSuperclass
public class Persona {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @Column(name="nombres")
	 private String nombres;
	 
	 @Column(name="apellidos")
	 private String apellidos;
	 
	 @Column(name="dni")
	 private String dni;
	 
	 @Column(name="telefono")
	 private String telefono;
	 
	 @Column(name="direccion")
	 private String direccion;
	 
	 @Column(name="estado")
	 private Boolean estado = true;
	 
	 public Long getId() {
		 return id;
	 }
	 public void setId(Long id) {
		 this.id = id;
	 }
	 public String getNombres() {
		 return nombres;
	 }
	 public void setNombres(String nombres) {
		 this.nombres = nombres;
	 }
	 public String getApellidos() {
		 return apellidos;
	 }
	 public void setApellidos(String apellidos) {
		 this.apellidos = apellidos;
	 }
	 public String getDni() {
		 return dni;
	 }
	 public void setDni(String dni) {
		 this.dni = dni;
	 }
	 public String getTelefono() {
		 return telefono;
	 }
	 public void setTelefono(String telefono) {
		 this.telefono = telefono;
	 }
	 public String getDireccion() {
		 return direccion;
	 }
	 public void setDireccion(String direccion) {
		 this.direccion = direccion;
	 }
	 public Boolean getEstado() {
		 return estado;
	 }
	 public void setEstado(Boolean estado) {
		 this.estado = estado;
	 }

	 
	
}
