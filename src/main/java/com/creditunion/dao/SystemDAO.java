package com.creditunion.dao;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SystemDAO {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public SystemDAO() {
    }

    public void persist(System system) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(system);
        em.getTransaction().commit();
        em.close();
    }

    public void removeSystem(System system) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(system));
        em.getTransaction().commit();
        em.close();
    }

    public System merge(System system) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        System updatedSystem = em.merge(system);
        em.getTransaction().commit();
        em.close();
        return updatedSystem;
    }

    public List<System> getAllSystems() {
        EntityManager em = emf.createEntityManager();
        List<System> systems = em.createQuery("from System", System.class).getResultList();
        em.close();
        return systems;
    }

    public System getSystemById(int id) {
        EntityManager em = emf.createEntityManager();
        System system = em.find(System.class, id);
        em.close();
        return system;
    }
}
