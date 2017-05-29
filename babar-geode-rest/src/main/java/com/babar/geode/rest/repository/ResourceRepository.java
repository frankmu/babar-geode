package com.babar.geode.rest.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.babar.geode.rest.model.Resource;

@RepositoryRestResource(collectionResourceRel = "resource", path = "resource")
public interface ResourceRepository extends GemfireRepository<Resource, String> {

}
