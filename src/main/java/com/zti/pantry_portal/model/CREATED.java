package com.zti.pantry_portal.model;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class CREATED {
    @RelationshipId
    @GeneratedValue
    private Long id;

    @TargetNode
    private Recipe recipe;
}

