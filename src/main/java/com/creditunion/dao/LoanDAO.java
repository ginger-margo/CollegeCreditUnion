package com.creditunion.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.creditunion.dao.*;
import com.creditunion.entities.Loan;

public class LoanDAO {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public LoanDAO() {
    }

    public void persist(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(loan);
        em.getTransaction().commit();
        em.close();
    }

    public void removeLoan(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(loan));
        em.getTransaction().commit();
        em.close();
    }

    public Loan merge(Loan loan) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Loan updatedLoan = em.merge(loan);
        em.getTransaction().commit();
        em.close();
        return updatedLoan;
    }

    public List<Loan> getAllLoans() {
        EntityManager em = emf.createEntityManager();
        List<Loan> loans = em.createQuery("from Loan", Loan.class).getResultList();
        em.close();
        return loans;
    }

    public Loan getLoanById(int id) {
        EntityManager em = emf.createEntityManager();
        Loan loan = em.find(Loan.class, id);
        em.close();
        return loan;
    }
}
