package com.example.adproyect.Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "estado")
    private String estado;

    @Column(name = "precio_total")
    private double precioTotal;

    @Column(name = "idcliente")
    private long idcliente;

    @Column(name="idhabitacion")
    private long idhabitacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    public Reserva() {
    }

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, double precioTotal, long idcliente, long idhabitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioTotal = precioTotal;
        this.idcliente = idcliente;
        this.idhabitacion = idhabitacion;
    }

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, String estado, double precioTotal, long idcliente, long idhabitacion, Cliente cliente, Habitacion habitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
        this.precioTotal = precioTotal;
        this.idcliente = idcliente;
        this.idhabitacion = idhabitacion;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, double precioTotal, long idcliente, long idhabitacion, Cliente cliente, Habitacion habitacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioTotal = precioTotal;
        this.idcliente = idcliente;
        this.idhabitacion = idhabitacion;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(long idcliente) {
        this.idcliente = idcliente;
    }

    public long getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(long idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", fechaEntrada=" + fechaEntrada +
                ", fechaSalida=" + fechaSalida +
                ", estado='" + estado + '\'' +
                ", precioTotal=" + precioTotal +
                ", idcliente=" + idcliente +
                ", idhabitacion=" + idhabitacion +
                ", cliente=" + cliente +
                ", habitacion=" + habitacion +
                '}';
    }
}

