package com.creditunion.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.example.dit.model.Student;

public class StudentDAO {

    protected static EntityManagerFactory emf = Persistence.createEntityManagerFactory("mydb");

    public StudentDAO() {
    }

    public void persist(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public void removeStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(student));
        em.getTransaction().commit();
        em.close();
    }

    public Student merge(Student student) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student updatedStudent = em.merge(student);
        em.getTransaction().commit();
        em.close();
        return updatedStudent;
    }

    public List<Student> getAllStudents() {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createQuery("from Student", Student.class).getResultList();
        em.close();
        return students;
    }

    public Student getStudentById(int id) {
        EntityManager em = emf.createEntityManager();
        Student student = em.find(Student.class, id);
        em.close();
        return student;
    }
}
