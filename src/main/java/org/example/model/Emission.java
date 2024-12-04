package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Table
@Entity
@Data
@AllArgsConstructor
@Builder //flexible constructor
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
    private String gasUnits;
    @Column
    private String nk;
    @Column
    private double value;

    @ManyToOne
    @JoinColumn(name = "year_report_id", referencedColumnName = "id")
    private YearReport yearReport;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
