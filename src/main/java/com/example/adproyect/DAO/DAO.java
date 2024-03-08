package com.example.adproyect.DAO;

public interface DAO<T> {
    T findById(Long id);
    void save(T reserva);
    void update(T reserva);
    void delete(T reserva);
}

