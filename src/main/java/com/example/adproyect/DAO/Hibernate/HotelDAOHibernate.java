package com.example.adproyect.DAO.Hibernate;

import com.example.adproyect.DAO.DAO;
import jakarta.persistence.*;
import com.example.adproyect.Entidades.Hotel;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HotelDAOHibernate implements DAO<Hotel> {

    private EntityManagerFactory entityManagerFactory;

    public HotelDAOHibernate() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("proyectoAD");
    }

    @Override
    public Hotel findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Hotel.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(hotel);
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

    @Override
    public void update(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(hotel);
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

    @Override
    public void delete(Hotel hotel) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(hotel) ? hotel : entityManager.merge(hotel));
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

    public List<Hotel> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Session session = entityManager.unwrap(Session.class);
        Query<Hotel> query = session.createQuery("SELECT e FROM Hotel e", Hotel.class);
        return query.getResultList();
    }
}



