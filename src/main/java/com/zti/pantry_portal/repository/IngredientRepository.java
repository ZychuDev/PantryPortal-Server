package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Ingredient;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface IngredientRepository extends Neo4jRepository<Ingredient, Long> {
    @Query("MATCH(i:Ingredient) WHERE i.name = $name RETURN i")
    Optional<Ingredient> findIngredientByName(String name);
}
