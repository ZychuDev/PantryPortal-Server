package com.zti.pantry_portal.service;
import com.zti.pantry_portal.model.Measurement;
import com.zti.pantry_portal.repository.MeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService  {
    private final MeasurementRepository measurementRepository;


    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findMeasurementsByName(String name){
        return measurementRepository.findByName(name);
    }

    public void addMeasurement(Measurement measurement){
        measurementRepository.save(measurement);
    }
}
