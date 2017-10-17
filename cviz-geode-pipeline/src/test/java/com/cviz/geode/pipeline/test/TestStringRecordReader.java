package com.cviz.geode.pipeline.test;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.record.StringRecord;

public class TestStringRecordReader implements RecordReader {

	@Override
	public void open() throws Exception {

	}

	@Override
	public Record<String> readRecord() throws Exception {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Record> readRecords() throws Exception {
		List<Record> records = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			StringRecord record = new StringRecord(null, "Hello " + i);
			records.add(record);
		}
		return records;
	}

	@Override
	public void close() throws Exception {

	}
}