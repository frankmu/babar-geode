package com.cviz.geode.common.impl;

import java.util.Collections;
import java.util.List;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.common.domain.Alert;

@Service
public class AlertServiceImpl implements AlertService{

	@Autowired
	private ClientCache cvizClientCache;

	@SuppressWarnings("unchecked")
	@Override
	public List<Alert> findAllOrderByTimestampLimit(int limit) {
		try {
			QueryService queryService = cvizClientCache.getQueryService();
	        Query query = queryService.newQuery("<trace> select * from /alert order by alertTime desc limit " + limit);
	        SelectResults<Alert> result = (SelectResults<Alert>) query.execute();
			return result.asList();
		} catch (FunctionDomainException | TypeMismatchException | NameResolutionException | QueryInvocationTargetException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Alert save(String id, Alert alert) {
		Region<String, Alert> region = cvizClientCache.getRegion("alert");
		region.put(id, alert);
		return alert;
	}

	@Override
	public Alert remove(String id) {
		Region<String, Alert> region = cvizClientCache.getRegion("alert");
		Alert alert = region.remove(id);
		return alert;
	}

}
