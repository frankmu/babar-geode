package com.cviz.geode.pipeline.task;

import java.util.List;

import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.writer.RecordWriter;

class CvizNoOpRecordWriter implements RecordWriter {

    @Override
    public void open() throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

	@SuppressWarnings("rawtypes")
	@Override
    public void writeRecords(List<Record> records) throws Exception {

    }
}