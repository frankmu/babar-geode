package com.cviz.corrprocess.pipeline;

import com.cviz.geode.common.domain.Alert;
import com.cviz.geode.common.pipeline.domain.CvizAlertRecord;
import com.cviz.geode.pipeline.processor.RecordFilter;

public class CvizCorrTestProcessor implements RecordFilter<CvizAlertRecord> {

	@Override
	public CvizAlertRecord processRecord(CvizAlertRecord record) {
		Alert alert = record.getPayload();
		alert.getAlertUID();
		return null;
	}
}