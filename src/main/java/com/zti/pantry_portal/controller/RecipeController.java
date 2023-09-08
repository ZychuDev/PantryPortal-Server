package com.zti.pantry_portal.controller;

import com.zti.pantry_portal.model.Amount;
import com.zti.pantry_portal.model.Ingredient;
import com.zti.pantry_portal.model.Recipe;
import com.zti.pantry_portal.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//DTO object
class RequestData{
    private String recipe_name;
    private String ingredient_name;
    private Integer weight;

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}

@CrossOrigin
@RestController
@RequestMapping("/api/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity addRecipe(@RequestBody Recipe recipe, Authentication authentication){
        System.out.println(recipe.getInstruction());
        recipeService.addRecipe(recipe, authentication.getName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/names")
    public ResponseEntity getAllRecipeNames(){
        return ResponseEntity.ok(recipeService.getAllRecipeNames());
    }

    @GetMapping("{name}")
    public ResponseEntity getRecipeByName(@PathVariable String name){
        return ResponseEntity.ok(recipeService.getRecipeByName(name));
    }

    @PatchMapping("{name}")
    public ResponseEntity patchInstruction(@PathVariable String name, @RequestBody String instruction){
        int end = instruction.length()-6;
        recipeService.updateInstruction(name, instruction.substring(instruction.lastIndexOf("{")+19,end));
        return ResponseEntity.ok("Instruction updated successfully");
    }

    @DeleteMapping ("{name}")
    public ResponseEntity deleteRecipeByName(@PathVariable String name){
        recipeService.deleteRecipeByName(name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/ingredients/{name}")
    public ResponseEntity getIngredients(@PathVariable String name){
        List<Amount> l = recipeService.getRecipeIngredients(name);
//        Amount a = l.get(1);
//        System.out.println(a.getName());
        return ResponseEntity.ok(l);
    }

    @PostMapping("/ingredients")
    public ResponseEntity addIngredient(@RequestBody RequestData requestData){
        recipeService.addRecipeIngredient(requestData.getRecipe_name() , requestData.getIngredient_name(), requestData.getWeight());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/ingredients/{recipe}/{ingredient}")
    public ResponseEntity deleteIngredient(@PathVariable String recipe, @PathVariable String ingredient){
        recipeService.deleteRecipeIngredient(recipe , ingredient);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
