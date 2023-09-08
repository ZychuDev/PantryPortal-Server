package com.zti.pantry_portal;

import com.zti.pantry_portal.config.RsaKeyProperties;
import com.zti.pantry_portal.model.Person;
import com.zti.pantry_portal.model.Recipe;
import com.zti.pantry_portal.repository.PersonRepository;
import com.zti.pantry_portal.repository.RecipeRepository;
import com.zti.pantry_portal.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class MyCommandLineRunner implements CommandLineRunner {
    private final RecipeRepository recipeRepository;
    private final PersonRepository personRepository;

    public MyCommandLineRunner(RecipeRepository recipeRepository, PersonRepository personRepository) {
        this.recipeRepository = recipeRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Your code to be executed on application startup
//        System.out.println("Application started. Your custom logic here.");
//        Recipe r = new Recipe();
//        r.setName("Kasza");
//        recipeRepository.save(r);
//        recipeRepository.assignAuthor(r.getName(), "Andrzej");
//        System.out.println("Kasza andrzeja");
//        System.out.println((personRepository.findById(1L).orElseThrow().getRecipes().get(0).getClass().getName()));
//        System.out.println("Kasza zrobiona jest z ");
//        recipeRepository.addRecipeIngredient(r.getName(), "Sugar", 120);
//        System.out.println((recipeRepository.findByName("Kasza").orElseThrow().getIngredients()));

    }
}

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
@RestController
@CrossOrigin
public class PantryPortalApplication {


    public static void main(String[] args) {
        SpringApplication.run(PantryPortalApplication.class, args);
    }

    @GetMapping("/")
    public String hello(Principal principal){

        return "Hello, " + principal.getName();
    }



}
