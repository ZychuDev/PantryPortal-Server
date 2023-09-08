package com.zti.pantry_portal.model;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Amount {
    @RelationshipId
    @GeneratedValue
    private String id;

    private final Integer weight;

    public String getName() {
        return name;
    }

    private final String name;

    @TargetNode
    private final Ingredient ing;

    public Amount(Ingredient ing, Integer weight, String name) {
        this.ing = ing;
        this.weight = weight;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getWeight() {
        return weight;
    }


}
