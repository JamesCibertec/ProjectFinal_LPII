package com.example.demo.Models;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalTime;

import jakarta.persistence.*;

@Entity
@Table(name="reservas")
public class ReservaAlquiler {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id_reserva;
	 
	 @ManyToOne
	 @JoinColumn(name = "id_cliente")
	 private Cliente cliente;
	 
	 @ManyToOne
	 @JoinColumn(name = "id_cancha")
	 private Canchas canchas;
	 
	 @Column(name = "fecha")
	 private Date fecha;
	 
	 @Column(name = "hora_inicio")
	 private String  hora_inicio;
	 
	 @Column(name = "hora_fin")
	 private String  hora_fin;
	 
	 @Column(name = "estado")
	 private String estado;
	 
	 @ManyToOne
	 @JoinColumn(name = "tipo_pago_id")
	 private TipoPago tipopago;
	 
	 @Column(name = "monto")
	 private BigDecimal monto;

	 public Long getId_reserva() {
		 return id_reserva;
	 }

	 public void setId_reserva(Long id_reserva) {
		 this.id_reserva = id_reserva;
	 }

	 public Cliente getCliente() {
		 return cliente;
	 }

	 public void setCliente(Cliente cliente) {
		 this.cliente = cliente;
	 }

	 public Canchas getCanchas() {
		 return canchas;
	 }

	 public void setCanchas(Canchas canchas) {
		 this.canchas = canchas;
	 }

	 public Date getFecha() {
		 return fecha;
	 }

	 public void setFecha(Date fecha) {
		 this.fecha = fecha;
	 }

	 public String  getHora_inicio() {
		 return hora_inicio;
	 }

	 public void setHora_inicio(String  hora_inicio) {
		 this.hora_inicio = hora_inicio;
	 }

	 public String  getHora_fin() {
		 return hora_fin;
	 }

	 public void setHora_fin(String  hora_fin) {
		 this.hora_fin = hora_fin;
	 }

	 public String getEstado() {
		 return estado;
	 }

	 public void setEstado(String estado) {
		 this.estado = estado;
	 }

	 public TipoPago getTipopago() {
		 return tipopago;
	 }

	 public void setTipopago(TipoPago tipopago) {
		 this.tipopago = tipopago;
	 }

	 public BigDecimal getMonto() {
		 return monto;
	 }

	 public void setMonto(BigDecimal monto) {
		 this.monto = monto;
	 }
	 
	 
	
}
