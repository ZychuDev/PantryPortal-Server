package com.zti.pantry_portal.service;

import com.zti.pantry_portal.controller.IngredientController;
import com.zti.pantry_portal.model.Ingredient;
import com.zti.pantry_portal.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository){
        this.ingredientRepository = ingredientRepository;
    }

    public void addIngredient(Ingredient ingredient){
        try{
            this.getIngredientByName(ingredient.getName());
            throw new ArithmeticException("Ingredient with this name already exists");
        } catch(RuntimeException e){
            if (e.getMessage().equals("Ingredient with this name already exists")){
                throw e;
            }
            ingredientRepository.save(ingredient);
        }

    }

    public void updateIngredient(Ingredient ingredient){
        Ingredient savedIngredient = ingredientRepository.findById(ingredient.getId()).orElseThrow(() -> new RuntimeException(
                String.format("Can not Find Ingredient by ID %s", ingredient.getId())
        ));

        savedIngredient.setName(ingredient.getName());
        savedIngredient.setKcal(ingredient.getKcal());
        savedIngredient.setCarb(ingredient.getCarb());
        savedIngredient.setFat(ingredient.getFat());
        savedIngredient.setProtein(ingredient.getProtein());

        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients(){
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientByName(String name){
        return ingredientRepository.findIngredientByName(name).orElseThrow(() -> new RuntimeException(
                String.format("Cannot Find Ingredient by Name %s", name)
        ));
    }

    public void deleteIngredient(Long id){
        ingredientRepository.deleteById(id);
    }
}
