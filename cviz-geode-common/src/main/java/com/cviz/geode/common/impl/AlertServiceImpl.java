package com.cviz.geode.common.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.TransactionException;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.cache.domain.AlertHistory;
import com.cviz.geode.cache.util.CvizGeodeRegionConstant;
import com.cviz.geode.common.api.AlertService;
import com.cviz.geode.common.util.AlertHistoryBuilder;

@Service
public class AlertServiceImpl implements AlertService {

	@Value("${geode.max.transaction.failures:10}")
	private int MAX_TRANSACTION_FAILURES;

	@Autowired
	private ClientCache cvizClientCache;

	private final Log logger = LogFactory.getLog(AlertServiceImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Alert> findAllOrderByReceiveTimeDescLimit(int limit) {
		try {
			// specify the query string
			String queryString = "<TRACE> SELECT * FROM /alert ORDER BY receiveTime DESC LIMIT $1";
			QueryService queryService = cvizClientCache.getQueryService();
			Query query = queryService.newQuery(queryString);

			// set query bind parameters
			Object[] params = new Object[1];
			params[0] = limit;

			SelectResults<Alert> result = (SelectResults<Alert>) query.execute(params);
			return result.asList();
		} catch (FunctionDomainException | TypeMismatchException | NameResolutionException
				| QueryInvocationTargetException e) {
			logger.error(e.getStackTrace());
		}
		return Collections.emptyList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alert> findByReceiveTimeGreaterThanAndReceiveTimeLessThanOrderByReceiveTimeDescLimit(Long startTime,
			Long endTime, int limit) {
		try {
			// specify the query string
			String queryString = "<TRACE> SELECT * FROM /alert WHERE receiveTime > $1 AND receiveTime < $2 ORDER BY receiveTime DESC LIMIT $3";
			QueryService queryService = cvizClientCache.getQueryService();
			Query query = queryService.newQuery(queryString);

			// set query bind parameters
			Object[] params = { startTime, endTime, limit };

			SelectResults<Alert> result = (SelectResults<Alert>) query.execute(params);
			return result.asList();
		} catch (FunctionDomainException | TypeMismatchException | NameResolutionException
				| QueryInvocationTargetException e) {
			logger.error(e.getStackTrace());
		}
		return Collections.emptyList();
	}

	@Override
	public Alert save(String id, Alert alert) {
		Region<String, Alert> region = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionAlert);
		region.put(id, alert);
		return alert;
	}

	@Override
	public boolean saveAll(List<Alert> alerts) {
		CacheTransactionManager txManager = cvizClientCache.getCacheTransactionManager();
		Region<String, Alert> alertRegion = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionAlert);
		Region<String, AlertHistory> alertHistoryRegion = cvizClientCache.getRegion(CvizGeodeRegionConstant.RegionAlertHistory);

		int failureCount = 0;
		while (true) {
			txManager.begin();
			try {
				for (Alert alert : alerts) {
					if (alertRegion.containsValueForKey(alert.getCompressKey())) {
						// Update active alert record in alert region
						Alert newAlert = alertRegion.get(alert.getCompressKey());
						newAlert.setAlertTally(newAlert.getAlertTally() + 1);
						alertRegion.put(alert.getCompressKey(), newAlert);

						// Insert alert history record to alerthistory region
						AlertHistory alertHistory = AlertHistoryBuilder.createAlertHistory(alert);
						alertHistoryRegion.put(alertHistory.getAlertUID(), alertHistory);
					} else {
						alert.setAlertTally(0);
						alertRegion.put(alert.getCompressKey(), alert);
					}
				}
				txManager.commit();
				return true;
			} catch (TransactionException e) {
				if (txManager.exists()) {
					txManager.rollback();
				}
				logger.info("Detect commit conflict, will retry.");

				if (++failureCount > MAX_TRANSACTION_FAILURES) {
					return false;
				}
			}
		}
	}

	@Override
	public Alert remove(String id) {
		Region<String, Alert> region = cvizClientCache.getRegion("alert");
		Alert alert = region.remove(id);
		return alert;
	}
}
