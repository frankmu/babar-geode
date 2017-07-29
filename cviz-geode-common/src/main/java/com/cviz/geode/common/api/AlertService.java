package com.cviz.geode.common.api;

import java.util.List;

import com.cviz.geode.common.domain.Alert;

public interface AlertService {

	List<Alert> findAllOrderByTimestampLimit(int limit);

	Alert save(String id, Alert alert);

	Alert remove(String id);
}
