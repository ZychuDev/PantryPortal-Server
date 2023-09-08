package com.zti.pantry_portal.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Node
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String instruction;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }



    @Relationship(type="CONTAIN", direction= Relationship.Direction.OUTGOING)
    private List<Amount> contains;

    public List<Amount> getContains() {
        return contains;
    }

    public Long getId() {
        return id;
    }

    public void setContains(List<Amount> ingredients) {
        this.contains = ingredients;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
