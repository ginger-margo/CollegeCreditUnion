package org.example.repository;

import org.example.model.Emission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmissionRepository extends CrudRepository<Emission, UUID> { // already has basic crud functionality
}
