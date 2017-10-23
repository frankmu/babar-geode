package com.cviz.geode.cache.listener;

import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.asyncqueue.AsyncEvent;
import org.apache.geode.cache.asyncqueue.AsyncEventListener;
import org.apache.geode.cache.query.FunctionDomainException;
import org.apache.geode.cache.query.NameResolutionException;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryInvocationTargetException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.TypeMismatchException;

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.cache.util.CvizGeodeRegionConstant;

public class CvizGeodeCompactAsyncEventListener implements AsyncEventListener, Declarable {

	private final Log logger = LogFactory.getLog(CvizGeodeCompactAsyncEventListener.class);

	public CvizGeodeCompactAsyncEventListener() {
	}

	@Override
	public void init(Properties props) {
	}

	@Override
	public void close() {
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean processEvents(List<AsyncEvent> events) {
		logger.info("Start processing async events with count: " + events.size());
		for (AsyncEvent event : events) {
			final Region region = event.getRegion();
			if (event.getOperation().isCreate() && region.getName().equalsIgnoreCase(CvizGeodeRegionConstant.RegionAlert)) {
//				try {
//					final String key = (String) event.getKey();
//					Alert alert = (Alert) event.getDeserializedValue();
//					QueryService queryService = event.getRegion().getRegionService().getQueryService();
//					String queryString = "SELECT * FROM /alert WHERE receiveTime > $1 AND receiveTime < $2 ORDER BY receiveTime DESC LIMIT $3";
//					Query query = queryService.newQuery(queryString);
//
//					// set query bind parameters
//					Object[] params = {""};
//
//					SelectResults<Alert> result = (SelectResults<Alert>) query.execute(params);
//					for(Alert existingAlert : result) {
//						alert.setAlertTally(existingAlert.getAlertTally());
//					}
//					logger.info("Compacting " + result.size() + " records: " + result.toString());
//					region.put(key, alert);
//
//				} catch (FunctionDomainException | TypeMismatchException | NameResolutionException
//						| QueryInvocationTargetException e) {
//					logger.error(e.getMessage());
//				}
			}
		}
		return true;
	}
}
