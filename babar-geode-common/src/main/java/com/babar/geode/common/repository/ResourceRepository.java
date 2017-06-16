package com.babar.geode.common.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.babar.geode.common.model.Resource;

public interface ResourceRepository extends GemfireRepository<Resource, String> {

}
