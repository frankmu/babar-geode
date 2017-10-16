package com.cviz.geode.pipeline.writer;

import com.cviz.geode.pipeline.record.Records;

public interface RecordWriter {

    void open() throws Exception;

    void writeRecords(Records records) throws Exception;

    void close() throws Exception;
}