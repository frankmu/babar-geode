package com.babar.geode.common.repository;

import org.springframework.data.gemfire.repository.GemfireRepository;

import com.babar.geode.common.model.Alert;

public interface AlertRepository extends GemfireRepository<Alert, String> {

}
