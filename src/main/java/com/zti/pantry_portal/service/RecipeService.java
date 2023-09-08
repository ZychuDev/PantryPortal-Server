package com.zti.pantry_portal.service;

import com.zti.pantry_portal.model.Amount;
import com.zti.pantry_portal.model.Recipe;
import com.zti.pantry_portal.repository.AmountRepository;
import com.zti.pantry_portal.repository.IngredientRepository;
import com.zti.pantry_portal.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final AmountRepository amountRepository;

    public RecipeService(RecipeRepository recipeRepository, AmountRepository amountRepository, IngredientRepository ingredientRepository){this.recipeRepository = recipeRepository;
        this.amountRepository = amountRepository;
    }


    public void addRecipe(Recipe recipe, String authorName){
        recipeRepository.save(recipe);
        recipeRepository.assignAuthor(recipe.getName(), authorName);
    }

    public Recipe getRecipeByName(String name){
        return recipeRepository.findByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Recipe with name: %s", name)
        ));
    }

    public List<String> getAllRecipeNames(){
        return recipeRepository.getAllRecipeNames();
    }


    public void addRecipeIngredient(String recipe_name, String ingredient_name, Integer weight){
        recipeRepository.addRecipeIngredient(recipe_name, ingredient_name, weight);

    }

    public void deleteRecipeIngredient(String recipe_name, String ingredient_name){
        recipeRepository.deleteRecipeIngredient(recipe_name, ingredient_name);
    }

    public void updateInstruction(String recipeName, String instruction){
        recipeRepository.updateInstruction(recipeName, instruction);
    }
    public void deleteRecipeByName(String name){
        recipeRepository.deleteRecipe(name);
    }

    public List<Amount> getRecipeIngredients(String name){
        return amountRepository.findRecipeIngredients(name);
    }
}
