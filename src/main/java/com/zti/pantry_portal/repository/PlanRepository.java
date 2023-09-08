package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PlanRepository extends MongoRepository<Plan, String> {
    @Query("{'name':  ?0}")
    List<Plan> findByName(String name);
}
