package com.cviz.geode.pipeline.reader;

import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.record.Records;


public interface RecordReader {

    void open() throws Exception;

    @SuppressWarnings("rawtypes")
	Record readRecord() throws Exception;

	Records readRecords() throws Exception;

    void close() throws Exception;
}