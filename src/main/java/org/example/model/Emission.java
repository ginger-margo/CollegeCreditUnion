package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //flexible constructor
public class Emission {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "year_report_id", referencedColumnName = "id")
    private YearReport yearReport;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
}
