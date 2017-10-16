package com.cviz.geode.pipeline.processor;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.record.Record;

@SuppressWarnings("rawtypes")
public class CompositeRecordProcessor implements RecordProcessor {

    private List<RecordProcessor> processors;

    public CompositeRecordProcessor() {
        this(new ArrayList<RecordProcessor>());
    }

    public CompositeRecordProcessor(List<RecordProcessor> processors) {
        this.processors = processors;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Record processRecord(Record record) throws Exception {
        Record processedRecord = record;
        for (RecordProcessor processor : processors) {
            processedRecord = processor.processRecord(processedRecord);
            if (processedRecord == null) {
                return null;
            }
        }
        return processedRecord;
    }

	public void addRecordProcessor(RecordProcessor recordProcessor) {
        processors.add(recordProcessor);
    }
}