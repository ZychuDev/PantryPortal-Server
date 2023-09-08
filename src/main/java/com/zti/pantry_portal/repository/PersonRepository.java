package com.zti.pantry_portal.repository;

import com.zti.pantry_portal.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository  extends Neo4jRepository<Person, Long> {
}
