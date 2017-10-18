package com.cviz.geode.common.impl;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

import com.cviz.geode.common.api.CvizGeodeRegionConstant;
import com.cviz.geode.common.api.PreProcRuleService;
import com.cviz.geode.common.domain.PreProcRule;

@Service
public class PreProcRuleServiceImpl implements PreProcRuleService {

	@Autowired
	private ClientCache cvizClientCache;

	@SuppressWarnings("unchecked")
	@Override
	public List<PreProcRule> getAll(int limit) {
		try {
			// specify the query string
			String queryString = "<TRACE> SELECT * FROM /" + CvizGeodeRegionConstant.RegionPreProcRule + " LIMIT $1";
			QueryService queryService = cvizClientCache.getQueryService();
			Query query = queryService.newQuery(queryString);

			// set query bind parameters
			Object[] params = new Object[1];
			params[0] = limit;

			SelectResults<PreProcRule> result = (SelectResults<PreProcRule>) query.execute(params);
			return result.asList();
		} catch (FunctionDomainException | TypeMismatchException | NameResolutionException
				| QueryInvocationTargetException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public PreProcRule get(String id) {
		Region<String, PreProcRule> region = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionPreProcRule);
		return region.get(id);
	}

	@Override
	public PreProcRule save(String id, PreProcRule preProcRule) {
		Region<String, PreProcRule> region = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionPreProcRule);
		region.put(id, preProcRule);
		return preProcRule;
	}

	@Override
	public PreProcRule create(PreProcRule preProcRule) {
		return save(UUID.randomUUID().toString(), preProcRule);
	}

	@Override
	public PreProcRule remove(String id) {
		Region<String, PreProcRule> region = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionPreProcRule);
		PreProcRule preProcRule = region.remove(id);
		return preProcRule;
	}
}