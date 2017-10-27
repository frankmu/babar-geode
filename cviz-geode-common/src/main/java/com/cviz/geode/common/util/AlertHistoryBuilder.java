package com.cviz.geode.common.util;

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.cache.domain.AlertHistory;

public class AlertHistoryBuilder {
	public static AlertHistory createAlertHistory(Alert alert) {
		AlertHistory alertHistory = new AlertHistory();
		alertHistory.setAlertFrom(alert.getAlertFrom());
		alertHistory.setAlertMsg(alert.getAlertMsg());
		alertHistory.setAlertObj(alert.getAlertObj());
		alertHistory.setAlertUID(alert.getAlertUID());
		alertHistory.setCompressKey(alert.getCompressKey());
		alertHistory.setFaultTime(alert.getFaultTime());
		alertHistory.setReceiveTime(alert.getReceiveTime());
		alertHistory.setSourceMsg(alert.getSourceMsg());
		return alertHistory;
	}
}
