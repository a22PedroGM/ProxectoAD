package com.example.adproyect.Servicio;

import com.example.adproyect.DAO.Hibernate.ClienteDAOHibernate;
import com.example.adproyect.Entidades.Cliente;

public class GestionClientes {
    private ClienteDAOHibernate clienteDAO;

    public GestionClientes(ClienteDAOHibernate clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteDAO.findById(id);
    }

    public void agregarCliente(Cliente cliente) {
        clienteDAO.save(cliente);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.update(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        clienteDAO.delete(cliente);
    }
}

