package com.babar.geode.rest.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.babar.geode.rest.model.Alert;

@RepositoryRestResource(collectionResourceRel = "alert", path = "alert")
public interface AlertRepository extends GemfireRepository<Alert, String> {

}
