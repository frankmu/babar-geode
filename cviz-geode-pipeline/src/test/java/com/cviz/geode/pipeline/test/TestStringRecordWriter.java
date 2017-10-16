package com.cviz.geode.pipeline.test;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.record.Records;
import com.cviz.geode.pipeline.record.StringRecord;
import com.cviz.geode.pipeline.writer.RecordWriter;

public class TestStringRecordWriter implements RecordWriter{
	
	private List<String> stringList;

	@Override
	public void open() throws Exception {
		stringList = new ArrayList<String>();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeRecords(Records records) throws Exception {
		for(Record record : records) {
			stringList.add(((StringRecord)record).getPayload());
		}
	}

	@Override
	public void close() throws Exception {
		
	}

	public List<String> getWriterResult(){
		return stringList;
	}
}
