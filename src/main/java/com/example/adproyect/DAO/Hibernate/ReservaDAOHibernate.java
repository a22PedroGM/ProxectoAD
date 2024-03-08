package com.example.adproyect.DAO.Hibernate;

import com.example.adproyect.Entidades.Reserva;
import jakarta.persistence.*;
import com.example.adproyect.DAO.DAO;

import java.util.List;

public class ReservaDAOHibernate implements DAO<Reserva> {

    private EntityManagerFactory entityManagerFactory;

    public ReservaDAOHibernate() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("proyectoAD");
    }

    @Override
    public Reserva findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Reserva.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Reserva reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(reserva);
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
    public void update(Reserva reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(reserva);
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
    public void delete(Reserva reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(reserva) ? reserva : entityManager.merge(reserva));
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

    public List<Reserva> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}


