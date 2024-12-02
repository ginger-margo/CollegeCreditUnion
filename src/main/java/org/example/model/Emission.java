package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Table
@Entity
@Data
@AllArgsConstructor
public class Emission {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private int year;
    @Column
    private String scenario;
    @Column
    private String gas_units;
    @Column
    private String nk;
    @Column
    private double value;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
