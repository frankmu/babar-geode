package com.cviz.geode.pipeline.record;

public class StringRecord extends GenericRecord<String> {

    public StringRecord(final Header header, final String payload) {
        super(header, payload);
    }
}