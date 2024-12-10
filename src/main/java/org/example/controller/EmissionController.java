package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.EmissionDTO;
import org.example.model.Emission;
import org.example.service.EmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller // entrance point from user journey
@RequiredArgsConstructor // constructor to initialize emissionService and others
@RequestMapping("/emissions")
public class EmissionController {

    private final EmissionService emissionService;

    // GET
    @GetMapping("/add-all")
    @Operation(summary = "add all emissions from data set")
    public ResponseEntity<Void> addAllCategories() {
        emissionService.addAllCategories();
        return ResponseEntity.ok().build(); //will return some metadata

    }

    @GetMapping("/all")
    @Operation(summary = "find all emissions")
    public ResponseEntity<List<Emission>> getAllEmissions(){
        List<Emission> emissions = emissionService.getAllEmissions();
        return ResponseEntity.ok(emissions); // returns it as a Response Entity
    }

    @GetMapping
    @Operation(summary = "find emission by id")
    public ResponseEntity<Emission> findEmissionById(@RequestParam("id") UUID id){
        Optional<Emission> emission = emissionService.findEmissionById(id); // using Optional because id can be null
        if (emission.isEmpty()) {
            return ResponseEntity.notFound().build(); //return code 404 if nothing found with id
        } else {
            return ResponseEntity.ok(emission.get()); // get emission from optional
        }
    }

    //POST

    @PostMapping
    @Operation(summary = "add emission")
    public ResponseEntity<Emission> addNewEmission(@RequestBody EmissionDTO emissionDTO){
        Emission emission = emissionService.addNewEmission(emissionDTO);
        return ResponseEntity.ok(emission);
    }

    @PutMapping
    @Operation(summary = "update emission")
    public ResponseEntity<Emission> updateEmission(@RequestParam("id") UUID id,
                                                   @RequestBody EmissionDTO emissionDTO){ //because EmissionDTO doesn't have an id it will be contained in the url
        try {
            Emission emission = emissionService.updateEmission(id, emissionDTO);
            return ResponseEntity.ok(emission);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE

    @DeleteMapping
    @Operation(summary = "delete emission")
    public ResponseEntity<String> deleteEmission(@RequestParam("id") UUID id) {
        emissionService.deleteEmission(id);
        return ResponseEntity.ok("deletion successful");
    }

}
