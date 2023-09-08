package com.zti.pantry_portal.controller;

import com.zti.pantry_portal.model.Ingredient;
import com.zti.pantry_portal.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/ingredient")
@CrossOrigin(originPatterns = "*")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService){this.ingredientService = ingredientService;}

    @PostMapping
    public ResponseEntity addIngredient(@RequestBody Ingredient ingredient){
        try{
            ingredientService.addIngredient(ingredient);
        } catch (ArithmeticException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity updateIngredient(@RequestBody Ingredient ingredient){
        ingredientService.updateIngredient(ingredient);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllIngredients(){
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Ingredient> getIngredientByName(@PathVariable String name){
        return ResponseEntity.ok(ingredientService.getIngredientByName(name));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteIngredient(@PathVariable Long id){
        ingredientService.deleteIngredient((id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
