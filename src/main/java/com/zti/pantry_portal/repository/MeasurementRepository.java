package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Measurement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MeasurementRepository extends MongoRepository<Measurement, String> {
    @Query("{'name':  ?0}")
    List<Measurement> findByName(String name);
}
