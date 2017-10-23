package com.cviz.corrprocess.test;

import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.common.pipeline.domain.CvizAlertRecord;
import com.cviz.geode.pipeline.processor.RecordFilter;

public class CvizTestProcessor implements RecordFilter<CvizAlertRecord> {

	@Override
	public CvizAlertRecord processRecord(CvizAlertRecord record) {
		Alert alert = record.getPayload();
		alert.setAlertUID(alert.getAlertUID() + alert.getAlertUID());
		return record;
	}
}