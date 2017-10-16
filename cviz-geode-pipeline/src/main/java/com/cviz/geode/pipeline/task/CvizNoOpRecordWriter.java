package com.cviz.geode.pipeline.task;

import com.cviz.geode.pipeline.record.Records;
import com.cviz.geode.pipeline.writer.RecordWriter;

class CvizNoOpRecordWriter implements RecordWriter {

    @Override
    public void open() throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeRecords(Records batch) throws Exception {

    }
}