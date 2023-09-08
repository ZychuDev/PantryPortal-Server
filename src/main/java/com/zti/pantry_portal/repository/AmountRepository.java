package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Amount;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmountRepository extends Neo4jRepository<Amount, String> {
    @Query("""
            MATCH 
            (r:Recipe)-[c:CONTAIN]->(i:Ingredient)
            WHERE r.name = $recipe_name 
            RETURN c{i, .weight, .name,  __nodeLabels__: labels(i), __elementId__: toString(id(c))};
            """)
    public List<Amount> findRecipeIngredients(String recipe_name);
}