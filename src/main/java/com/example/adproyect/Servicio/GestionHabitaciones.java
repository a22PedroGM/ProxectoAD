package com.example.adproyect.Servicio;

import com.example.adproyect.DAO.Hibernate.HabitacionDAOHibernate;
import com.example.adproyect.Entidades.Habitacion;

import java.util.List;

public class GestionHabitaciones {
    private HabitacionDAOHibernate habitacionDAO;

    public GestionHabitaciones(HabitacionDAOHibernate habitacionDAO) {
        this.habitacionDAO = habitacionDAO;
    }

    public Habitacion buscarHabitacionPorId(Long id) {
        return habitacionDAO.findById(id);
    }

    public void agregarHabitacion(Habitacion habitacion) {
        habitacionDAO.save(habitacion);
    }

    public void actualizarHabitacion(Habitacion habitacion) {
        habitacionDAO.update(habitacion);
    }

    public void eliminarHabitacion(Habitacion habitacion) {
        habitacionDAO.delete(habitacion);
    }

}

