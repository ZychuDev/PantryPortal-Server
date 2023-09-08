package com.zti.pantry_portal.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public List<CREATED> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<CREATED> recipes) {
        this.recipes = recipes;
    }

    @Relationship(type="CREATED", direction = Relationship.Direction.OUTGOING)
    private List<CREATED> recipes;

    public Person(String name){
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
