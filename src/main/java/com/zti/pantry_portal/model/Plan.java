package com.zti.pantry_portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document("plan")
public class Plan {
    @Id
    private String id;

    @Field(name = "name")
    private String name;

    @Field(name = "recipes")
    private List<String> recipes;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public void appendRecipe(String recipe){
        if(this.recipes == null || this.recipes.size() == 0){
            this.recipes = new ArrayList<>();
        }
        this.recipes.add(recipe);
    }

    public void removeRecipe(String recipe){
        this.recipes.remove(this.recipes.indexOf(recipe));
    }
    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }
}
