package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Ingredient;
import com.zti.pantry_portal.model.Recipe;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends Neo4jRepository<Recipe, Long> {
    @Query("MATCH(r:Recipe) WHERE r.name = $name RETURN r")
    Optional<Recipe> findByName(String name);

    @Query("""
            MATCH 
            (r:Recipe),
            (i:Ingredient)
            WHERE r.name = $recipe_name AND i.name = $ingredient_name
            CREATE (r)-[c:CONTAIN {weight: $weight, name: $ingredient_name}]->(i)
            CREATE (i)-[:IS_PART]->(r)
            RETURN r
            """)
    void addRecipeIngredient(String recipe_name, String ingredient_name, Integer weight);

    @Query("""
            MATCH 
            (r:Recipe),
            (i:Ingredient),
            (r)-[c:CONTAIN]->(i),
            (i)-[is:IS_PART]->(r)
            WHERE r.name = $recipe_name AND i.name = $ingredient_name
            DELETE is
            DELETE c
            """)
    void deleteRecipeIngredient(String recipe_name, String ingredient_name);

    @Query("MATCH (r:Recipe), (p:Person) WHERE p.name = $personName AND r.name = $recipeName CREATE (p)-[:CREATED]->(r)")
    void assignAuthor(String recipeName, String personName);

//    @Query("MATCH (r:Recipe {name: \"Cake\"})-[rel:CONTAIN]->(i:Ingredient) RETURN i;")
//    List<CONTAIN> getRecipeIngredients(String recipe_name);

    @Query("MATCH (r:Recipe) RETURN r.name")
    List<String> getAllRecipeNames();

    @Query("MATCH (r:Recipe)-[rel]-() WHERE r.name = $recipeName DELETE r, rel")
    void deleteRecipe(String recipeName);

    @Query("MATCH (r:Recipe) WHERE r.name = $recipeName SET r.instruction = $instruction")
    void updateInstruction(String recipeName, String instruction);


}
