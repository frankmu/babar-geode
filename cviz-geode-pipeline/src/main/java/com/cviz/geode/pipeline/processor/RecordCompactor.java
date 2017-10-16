package com.cviz.geode.pipeline.processor;

import com.cviz.geode.pipeline.record.StringRecord;

public abstract class RecordCompactor implements RecordProcessor<StringRecord, StringRecord> {

    protected static final String EMPTY_STRING = "";

    protected abstract String compact(final String payload);

    @Override
    public StringRecord processRecord(final StringRecord record) throws Exception {
        return new StringRecord(record.getHeader(), compact(record.getPayload()));
    }
}