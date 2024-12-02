package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.EmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // entrance point from user journey
@RequiredArgsConstructor // constructor to initialize emissionService and others
public class EmissionController {

    private final EmissionService emissionService;

    @GetMapping("/add-all")
    public ResponseEntity<Void> addAllCategories() {
        emissionService.addAllCategories();
        return ResponseEntity.ok().build(); //will return some metadata

    }

}
