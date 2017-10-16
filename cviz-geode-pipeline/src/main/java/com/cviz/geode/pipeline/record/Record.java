package com.cviz.geode.pipeline.record;

public interface Record<T> {
    Header getHeader();
    T getPayload();
}