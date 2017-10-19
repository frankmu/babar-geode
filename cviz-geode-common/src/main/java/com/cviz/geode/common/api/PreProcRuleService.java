package com.cviz.geode.common.api;

import java.util.List;

import com.cviz.geode.common.domain.PreProcRule;

public interface PreProcRuleService {

	List<PreProcRule> getAll(int limit);

	PreProcRule get(String id);

	PreProcRule create(PreProcRule preProcRule);

	PreProcRule save(String id, PreProcRule preProcRule);

	PreProcRule remove(String id);

	// TODO remove this service
	void createDemoData();
}