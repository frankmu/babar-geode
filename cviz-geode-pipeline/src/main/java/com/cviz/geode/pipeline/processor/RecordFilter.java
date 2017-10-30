package com.cviz.geode.pipeline.processor;

import com.cviz.geode.pipeline.record.Record;

public interface RecordFilter<R extends Record<?>> extends RecordPreProcessor<R, R> {

    @Override
    R processRecord(R record);
}