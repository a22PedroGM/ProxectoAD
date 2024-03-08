package com.example.adproyect.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "precio_por_noche")
    private double precioPorNoche;

    @Column(name = "disponibilidad")
    private boolean disponibilidad;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Habitacion() {
    }

    public Habitacion(String tipo, double precioPorNoche, boolean disponibilidad, Hotel hotel) {
        this.tipo = tipo;
        this.precioPorNoche = precioPorNoche;
        this.disponibilidad = disponibilidad;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecioPorNoche() {
        return precioPorNoche;
    }

    public void setPrecioPorNoche(double precioPorNoche) {
        this.precioPorNoche = precioPorNoche;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                ", disponibilidad=" + disponibilidad +
                ", hotel=" + hotel +
                '}';
    }
}
