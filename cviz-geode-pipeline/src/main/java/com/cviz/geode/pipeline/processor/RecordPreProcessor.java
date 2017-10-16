package com.cviz.geode.pipeline.processor;

import com.cviz.geode.pipeline.record.Record;

public interface RecordPreProcessor<I extends Record<?>, O extends Record<?>> {

    O processRecord(I record) throws Exception;
}