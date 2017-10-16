package com.cviz.geode.pipeline.processor;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.record.Record;

public class RecordCollector<P> implements RecordProcessor<Record<P>, Record<P>> {

    private List<Record<P>> records = new ArrayList<>();

    @Override
    public Record<P> processRecord(final Record<P> record) {
        records.add(record);
        return record;
    }

    public List<Record<P>> getRecords() {
        return records;
    }
}