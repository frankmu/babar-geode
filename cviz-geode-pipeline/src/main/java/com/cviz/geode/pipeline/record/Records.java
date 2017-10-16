package com.cviz.geode.pipeline.record;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.addAll;

@SuppressWarnings("rawtypes")
public class Records implements Iterable<Record> {

	private List<Record> records = new ArrayList<>();

    public Records() {
    }

    public Records(Record... records) {
        addAll(this.records, records);
    }

    public Records(List<Record> records) {
        this.records = records;
    }

    public void addRecord(final Record record) {
        records.add(record);
    }

    public void removeRecord(final Record record) {
        records.remove(record);
    }

    public boolean isEmpty() {
        return records.isEmpty();
    }

    public long size() {
        return records.size();
    }

	@Override
	public Iterator<Record> iterator() {
		return records.iterator();
	}
}