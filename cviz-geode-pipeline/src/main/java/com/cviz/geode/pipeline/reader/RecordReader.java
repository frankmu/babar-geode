package com.cviz.geode.pipeline.reader;

import java.util.List;

import com.cviz.geode.pipeline.record.Record;


public interface RecordReader {

    void open() throws Exception;

	@SuppressWarnings("rawtypes")
	Record readRecord() throws Exception;

	@SuppressWarnings("rawtypes")
	List<Record> readRecords() throws Exception;

    void close() throws Exception;
}