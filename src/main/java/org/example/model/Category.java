package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Entity
@Table //Table name or change in DB
@Data //using lombok to generate getters, setters, required args constructor and no args constructor
@AllArgsConstructor
public class Category {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String name;
    @Column
    private String description;



}
