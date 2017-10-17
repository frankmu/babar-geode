package com.cviz.corrprocess.pipeline;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.common.pipeline.domain.CvizAlertRecord;
import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Record;

public class CvizCorrInputRecordReader implements RecordReader {

	@SuppressWarnings("rawtypes")
	private List<Record> cvizCorrRecords;

	@SuppressWarnings("rawtypes")
	public CvizCorrInputRecordReader() {
		cvizCorrRecords = new ArrayList<Record>();
	}

	public CvizCorrInputRecordReader(List<CvizAlertRecord> cvizAlertRecords) {
		cvizCorrRecords = new ArrayList<>(cvizAlertRecords);
	}

	@Override
	public void open() throws Exception {
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Record readRecord() throws Exception {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Record> readRecords() throws Exception {
		return cvizCorrRecords;
	}

	@Override
	public void close() throws Exception {

	}
}