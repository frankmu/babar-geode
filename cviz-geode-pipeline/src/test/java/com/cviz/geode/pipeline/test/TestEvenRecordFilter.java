package com.cviz.geode.pipeline.test;

import java.util.Arrays;
import java.util.List;

import com.cviz.geode.pipeline.processor.RecordFilter;
import com.cviz.geode.pipeline.record.StringRecord;

public class TestEvenRecordFilter implements RecordFilter<StringRecord> {

	@Override
	public StringRecord processRecord(StringRecord record) {
		List<String> blackList = Arrays.asList("Hello 0", "Hello 2", "Hello 4", "Hello 6", "Hello 8");
		if(blackList.contains(record.getPayload())) {
			return null;
		}
		return record;
	}
}