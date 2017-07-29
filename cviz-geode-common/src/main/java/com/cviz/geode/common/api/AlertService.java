package com.cviz.geode.common.api;

import java.util.List;

import com.cviz.geode.common.domain.Alert;

public interface AlertService {

	List<Alert> findAllOrderByAlertTimeDescLimit(int limit);

	List<Alert> findByAlertTimeGreaterThanAndAlertTimeLessThanOrderByAlertTimeDescLimit(Long startTime, Long endTime, int limit);

	Alert save(String id, Alert alert);

	Alert remove(String id);
}
