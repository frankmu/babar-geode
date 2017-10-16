package com.cviz.geode.pipeline.record;

public class GenericRecord<P> implements Record<P> {

    protected Header header;
    protected P payload;

    public GenericRecord(final Header header, final P payload) {
        this.header = header;
        this.payload = payload;
    }

    @Override
    public Header getHeader() {
        return header;
    }

    @Override
    public P getPayload() {
        return payload;
    }
}