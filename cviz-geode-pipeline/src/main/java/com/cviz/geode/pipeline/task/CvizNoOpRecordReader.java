package com.cviz.geode.pipeline.task;

import java.util.List;

import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Record;

class CvizNoOpRecordReader implements RecordReader {
    @Override
    public void open() throws Exception {

    }

	@SuppressWarnings("rawtypes")
	@Override
    public Record readRecord() throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

	@SuppressWarnings("rawtypes")
	@Override
	public List<Record> readRecords() throws Exception {
		return null;
	}
}