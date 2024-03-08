package com.example.adproyect.Servicio;

import com.example.adproyect.DAO.Hibernate.ReservaDAOHibernate;
import com.example.adproyect.Entidades.Reserva;

import java.util.List;

public class GestionReservas {
    private ReservaDAOHibernate reservaDAO;

    public GestionReservas(ReservaDAOHibernate reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    public Reserva buscarReservaPorId(Long id) {
        return reservaDAO.findById(id);
    }

    public void hacerReserva(Reserva reserva) {
        reservaDAO.save(reserva);
    }

    public void actualizarReserva(Reserva reserva) {
        reservaDAO.update(reserva);
    }

    public void cancelarReserva(Reserva reserva) {
        reservaDAO.delete(reserva);
    }

}
