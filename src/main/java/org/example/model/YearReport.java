package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@Data
public class YearReport {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private int year;
    @Column
    private String country;
    @OneToMany(mappedBy = "yearReport", cascade = CascadeType.ALL)
    private List<Emission> emissions;




}
