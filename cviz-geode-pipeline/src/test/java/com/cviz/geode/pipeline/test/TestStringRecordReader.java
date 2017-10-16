package com.cviz.geode.pipeline.test;

import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.record.Records;
import com.cviz.geode.pipeline.record.StringRecord;

public class TestStringRecordReader implements RecordReader {

	@Override
	public void open() throws Exception {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Record readRecord() throws Exception {
		return null;
	}

	@Override
	public Records readRecords() throws Exception {
		Records records = new Records();
		for(int i = 0; i < 10; i++) {
			StringRecord record = new StringRecord(null, "Hello " + i);
			records.addRecord(record);
		}
		return records;
	}

	@Override
	public void close() throws Exception {

	}
}