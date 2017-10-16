package com.cviz.geode.pipeline.processor;

import java.util.ArrayList;
import java.util.List;

import com.cviz.geode.pipeline.record.Record;

@SuppressWarnings("rawtypes")
public class CompositeRecordPreProcessor implements RecordPreProcessor {

    private List<RecordPreProcessor> processors;

    public CompositeRecordPreProcessor() {
        this(new ArrayList<RecordPreProcessor>());
    }

    public CompositeRecordPreProcessor(List<RecordPreProcessor> processors) {
        this.processors = processors;
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public Record processRecord(Record record) throws Exception {
        Record processedRecord = record;
        for (RecordPreProcessor preProcessor : processors) {
            processedRecord = preProcessor.processRecord(processedRecord);
            if (processedRecord == null) {
                return null;
            }
        }
        return processedRecord;
    }

	public void addRecordProcessor(RecordPreProcessor recordPreProcessor) {
        processors.add(recordPreProcessor);
    }
}