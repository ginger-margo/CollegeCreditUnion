package com.creditunion.services;

import com.creditunion.dao.StudentDAO;
import com.creditunion.entities.Student;
import java.util.List;

public class StudentService {

    private final StudentDAO studentDAO = new StudentDAO();

    public void createStudent(Student student) {
        studentDAO.persist(student);
    }
    
    public Student getStudentById(int id) {
        return studentDAO.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public Student updateStudent(Student student) {
        return studentDAO.merge(student);
    }

    public boolean deleteStudent(int id) {
        Student student = studentDAO.getStudentById(id);
        if (student != null) {
            studentDAO.removeStudent(student);
            return true;
        }
        return false;
    }
}
