package com.creditunion.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students = new ArrayList<Student>();

    // Empty Constructor
    public System() {
    }

    // Constructor
    public System(int id, List<Student> students) {
        this.id = id;
        this.students = students;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
