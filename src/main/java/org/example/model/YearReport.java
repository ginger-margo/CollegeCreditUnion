package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
public class YearReport {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private int year;
    @Column
    private String country;
    @OneToMany(mappedBy = "year_report_id", cascade = CascadeType.ALL)
    private List<Emission> emissions;



}
