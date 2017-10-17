package com.cviz.geode.pipeline.writer;

import java.util.List;

import com.cviz.geode.pipeline.record.Record;

public interface RecordWriter {

    void open() throws Exception;

	@SuppressWarnings("rawtypes")
	void writeRecords(List<Record> records) throws Exception;

    void close() throws Exception;
}