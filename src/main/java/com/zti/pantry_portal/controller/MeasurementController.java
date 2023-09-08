package com.zti.pantry_portal.controller;

import com.zti.pantry_portal.model.Measurement;
import com.zti.pantry_portal.service.MeasurementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.Authentication;

@CrossOrigin
@RestController
@RequestMapping("/api/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PostMapping
    public ResponseEntity addMeasurement(Authentication authentication, @RequestBody Measurement measurement){
        measurementService.addMeasurement(measurement);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity getMeasurementByName(Authentication authentication){
        return ResponseEntity.ok(measurementService.findMeasurementsByName(authentication.getName()));
    }
}
