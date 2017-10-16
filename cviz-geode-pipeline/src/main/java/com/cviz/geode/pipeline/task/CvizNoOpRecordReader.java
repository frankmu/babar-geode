package com.cviz.geode.pipeline.task;

import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.record.Records;

class CvizNoOpRecordReader implements RecordReader {
    @Override
    public void open() throws Exception {

    }

	@Override
	@SuppressWarnings("rawtypes")
    public Record readRecord() throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

	@Override
	public Records readRecords() throws Exception {
		return null;
	}
}