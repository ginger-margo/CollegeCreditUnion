package com.creditunion.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.example.dit.model.Deposit;

public class DepositDAO {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public DepositDAO() {
    }

    public void persist(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(deposit);
        em.getTransaction().commit();
        em.close();
    }

    public void removeDeposit(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(deposit));
        em.getTransaction().commit();
        em.close();
    }

    public Deposit merge(Deposit deposit) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Deposit updatedDeposit = em.merge(deposit);
        em.getTransaction().commit();
        em.close();
        return updatedDeposit;
    }

    public List<Deposit> getAllDeposits() {
        EntityManager em = emf.createEntityManager();
        List<Deposit> deposits = em.createQuery("from Deposit", Deposit.class).getResultList();
        em.close();
        return deposits;
    }

    public Deposit getDepositById(int id) {
        EntityManager em = emf.createEntityManager();
        Deposit deposit = em.find(Deposit.class, id);
        em.close();
        return deposit;
    }
}
