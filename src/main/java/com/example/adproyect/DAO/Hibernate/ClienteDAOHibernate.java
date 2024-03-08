package com.example.adproyect.DAO.Hibernate;

import com.example.adproyect.DAO.DAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import com.example.adproyect.Entidades.Cliente;

import java.util.List;

public class ClienteDAOHibernate implements DAO<Cliente> {
    private EntityManagerFactory entityManagerFactory;

    public ClienteDAOHibernate() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("proyectoAD");
    }

    @Override
    public Cliente findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Cliente cliente = null;
        try {
            cliente = entityManager.find(Cliente.class, id);
        } finally {
            entityManager.close();
        }
        return cliente;
    }

    @Override
    public void save(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(cliente);
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
    public void update(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(cliente);
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
    public void delete(Cliente cliente) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.remove(entityManager.contains(cliente) ? cliente : entityManager.merge(cliente));
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
    public List<Cliente> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}


