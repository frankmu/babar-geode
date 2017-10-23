package com.cviz.geode.common.api;

import java.util.List;

import com.cviz.geode.cache.domain.Alert;

public interface AlertService {

	List<Alert> findAllOrderByReceiveTimeDescLimit(int limit);

	List<Alert> findByReceiveTimeGreaterThanAndReceiveTimeLessThanOrderByReceiveTimeDescLimit(Long startTime, Long endTime, int limit);

	Alert save(String id, Alert alert);

	boolean saveAll(List<Alert> alerts);

	Alert remove(String id);
}
