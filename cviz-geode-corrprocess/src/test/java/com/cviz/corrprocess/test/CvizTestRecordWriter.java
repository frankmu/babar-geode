package com.cviz.corrprocess.test;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.writer.RecordWriter;

public class CvizTestRecordWriter implements RecordWriter{
	
	@SuppressWarnings("rawtypes")
	private List<Record> stringList;

	@SuppressWarnings("rawtypes")
	@Override
	public void open() throws Exception {
		stringList = new ArrayList<Record>();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void writeRecords(List<Record> records) throws Exception {
		for(Record record : records) {
			stringList.add(record);
		}
	}

	@Override
	public void close() throws Exception {
		
	}

	@SuppressWarnings("rawtypes")
	public List<Record> getWriterResult(){
		return stringList;
	}
}
