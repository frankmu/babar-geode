package com.cviz.geode.common.pipeline.domain;

import com.cviz.geode.common.domain.Alert;
import com.cviz.geode.pipeline.record.Header;
import com.cviz.geode.pipeline.record.Record;

public class CvizAlertRecord implements Record<Alert> {
	
	private Alert alert;
	
	public CvizAlertRecord(Alert alert) {
		this.alert = alert;
	}

	@Override
	public Header getHeader() {
		return null;
	}

	@Override
	public Alert getPayload() {
		return this.alert;
	}
}