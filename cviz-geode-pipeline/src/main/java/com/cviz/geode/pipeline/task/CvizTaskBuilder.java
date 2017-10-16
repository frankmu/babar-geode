package com.cviz.geode.pipeline.task;

import static com.cviz.geode.pipeline.util.Utils.checkNotNull;

import com.cviz.geode.pipeline.processor.RecordFilter;
import com.cviz.geode.pipeline.processor.RecordMapper;
import com.cviz.geode.pipeline.processor.RecordProcessor;
import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.writer.RecordWriter;

public final class CvizTaskBuilder {

    private CvizTask task;
    private TaskParameters parameters;

    public CvizTaskBuilder() {
        parameters = new TaskParameters();
        task = new CvizTask(parameters);
    }

    public static CvizTaskBuilder aNewJob() {
        return new CvizTaskBuilder();
    }

    public CvizTaskBuilder named(final String name) {
        checkNotNull(name, "task name");
        task.setName(name);
        return this;
    }

    public CvizTaskBuilder reader(final RecordReader recordReader) {
        checkNotNull(recordReader, "record reader");
        task.setRecordReader(recordReader);
        return this;
    }

    @SuppressWarnings("rawtypes")
	public CvizTaskBuilder filter(final RecordFilter recordFilter) {
        checkNotNull(recordFilter, "record filter");
        task.addRecordPreProcessor(recordFilter);
        return this;
    }

    @SuppressWarnings("rawtypes")
	public CvizTaskBuilder mapper(final RecordMapper recordMapper) {
        checkNotNull(recordMapper, "record mapper");
        task.addRecordProcessor(recordMapper);
        return this;
    }

    @SuppressWarnings("rawtypes")
	public CvizTaskBuilder processor(final RecordProcessor recordProcessor) {
        checkNotNull(recordProcessor, "record processor");
        task.addRecordProcessor(recordProcessor);
        return this;
    }

    public CvizTaskBuilder writer(final RecordWriter recordWriter) {
        checkNotNull(recordWriter, "record writer");
        task.setRecordWriter(recordWriter);
        return this;
    }

    public Task build() {
        return task;
    }
}