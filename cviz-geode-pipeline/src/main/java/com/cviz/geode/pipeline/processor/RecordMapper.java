package com.cviz.geode.pipeline.processor;

import com.cviz.geode.pipeline.record.Record;

public interface RecordMapper<I extends Record<?>, O extends Record<?>> extends RecordProcessor<I, O> {

    @Override
    O processRecord(I record) throws Exception;
}