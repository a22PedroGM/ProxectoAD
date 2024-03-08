package com.example.adproyect.DAO.Hibernate;

import com.example.adproyect.Entidades.Habitacion;
import jakarta.persistence.*;
import com.example.adproyect.DAO.DAO;


import java.util.List;

public class HabitacionDAOHibernate implements DAO<Habitacion> {

    private EntityManagerFactory entityManagerFactory;

    public HabitacionDAOHibernate() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("proyectoAD");
    }

    @Override
    public Habitacion findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Habitacion.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Habitacion habitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(habitacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción adecuadamente en tu aplicación
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void update(Habitacion habitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(habitacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción adecuadamente en tu aplicación
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Habitacion habitacion) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(habitacion) ? habitacion : entityManager.merge(habitacion));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List obtenerHabitacionesDisponibles() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT h FROM Habitacion h WHERE h.disponibilidad = true").getResultList();
        } finally {
            entityManager.close();
        }
    }

}

