package com.babar.geode.rest.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.gemfire.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.babar.geode.rest.model.Person;

@RepositoryRestResource(collectionResourceRel = "hello", path = "hello")
public interface ProductRepository extends GemfireRepository<Person, String> {

	List<Person> findByLastName(@Param("name") String name);
}
