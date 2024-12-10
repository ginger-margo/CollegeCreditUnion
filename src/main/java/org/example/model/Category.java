package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity // JPA entity - table definition
@Table //Usually in DB we use plurals (ie Categories??)
@Data //using lombok to generate getters, setters, required args constructor and no args constructor
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id // PK
    @GeneratedValue(generator = "UUID") //automatically generatod uuid
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator") // custom generator for uuid (not used??)
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;
    @Column
    private String name;
    @Column
    private String description;



}
