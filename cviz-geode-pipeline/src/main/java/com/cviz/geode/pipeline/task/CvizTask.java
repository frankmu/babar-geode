package com.cviz.geode.pipeline.task;

import com.cviz.geode.pipeline.processor.CompositeRecordPreProcessor;
import com.cviz.geode.pipeline.processor.CompositeRecordProcessor;
import com.cviz.geode.pipeline.processor.RecordPreProcessor;
import com.cviz.geode.pipeline.processor.RecordProcessor;
import com.cviz.geode.pipeline.reader.RecordReader;
import com.cviz.geode.pipeline.record.Records;
import com.cviz.geode.pipeline.writer.RecordWriter;
import com.cviz.geode.pipeline.record.Record;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.cviz.geode.pipeline.task.TaskStatus.*;

class CvizTask implements Task {

	private static final Logger LOGGER = Logger.getLogger(CvizTask.class.getName());
	private static final String DEFAULT_TASK_NAME = "cviz task";

	private String name;

	private RecordReader recordReader;
	private RecordWriter recordWriter;
	@SuppressWarnings("rawtypes")
	private RecordPreProcessor recordPreProcessor;
	@SuppressWarnings("rawtypes")
	private RecordProcessor recordProcessor;

	private TaskMetrics metrics;
	private TaskExecutionResult taskResult;

	CvizTask(TaskParameters parameters) {
		this.name = DEFAULT_TASK_NAME;
		metrics = new TaskMetrics();
		taskResult = new TaskExecutionResult();
		recordReader = new CvizNoOpRecordReader();
		recordPreProcessor = new CompositeRecordPreProcessor();
		recordProcessor = new CompositeRecordProcessor();
		recordWriter = new CvizNoOpRecordWriter();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public TaskExecutionResult call() {
		try {
			openReader();
			openWriter();
			Records processedRecords = readAndProcessRecords();
			writeRecords(processedRecords);
		} catch (Exception exception) {
			fail(exception);
			return taskResult;
		} finally {
			closeReader();
			closeWriter();
		}
		teardown(COMPLETED);
		return taskResult;
	}

	private void openReader() throws CvizPipelineException {
		try {
			LOGGER.log(Level.FINE, "Opening record reader");
			recordReader.open();
		} catch (Exception e) {
			throw new CvizPipelineException("Unable to open record reader", e);
		}
	}

	private void openWriter() throws CvizPipelineException {
		try {
			LOGGER.log(Level.FINE, "Opening record writer");
			recordWriter.open();
		} catch (Exception e) {
			throw new CvizPipelineException("Unable to open record writer", e);
		}
	}

	@SuppressWarnings("rawtypes")
	private Records readAndProcessRecords() throws CvizPipelineException {
		Records processedRecords = new Records();
		for (Record record : readRecords()) {
			processRecord(record, processedRecords);
		}
		return processedRecords;
	}

	private Records readRecords() throws CvizPipelineException {
		try {
			LOGGER.log(Level.FINE, "Reading records");
			return recordReader.readRecords();
		} catch (Exception e) {
			throw new CvizPipelineException("Unable to read records", e);
		}
	}

	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	private void processRecord(Record record, Records records) {
		Record processedRecord = null;
		try {
			LOGGER.log(Level.FINE, "Processing {0}", record);
			Record preProcessedRecord = recordPreProcessor.processRecord(record);
			if (preProcessedRecord == null) {
				LOGGER.log(Level.FINE, "{0} has been filtered", record);
				metrics.incrementFilteredCount();
			} else {
				processedRecord = recordProcessor.processRecord(preProcessedRecord);
				if (processedRecord == null) {
					LOGGER.log(Level.FINE, "{0} has been filtered", record);
					metrics.incrementFilteredCount();
				} else {
					records.addRecord(processedRecord);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Unable to process " + record, e);
			metrics.incrementErrorCount();
		}
	}

	private void writeRecords(Records records) throws CvizPipelineException {
		LOGGER.log(Level.FINE, "Writing {0}", records);
		try {
			if (!records.isEmpty()) {
				recordWriter.writeRecords(records);
				metrics.incrementWriteCount(records.size());
			}
		} catch (Exception e) {
			throw new CvizPipelineException("Unable to write records", e);
		}
	}

    private void teardown(TaskStatus status) {
    		taskResult.setStatus(status);
        metrics.setEndTime(System.currentTimeMillis());
        LOGGER.log(Level.INFO, "Job ''{0}'' finished with status: {1}", new Object[]{name, taskResult.getStatus()});
    }

	private void fail(Exception exception) {
		String reason = exception.getMessage();
		Throwable error = exception.getCause();
		LOGGER.log(Level.SEVERE, reason, error);
		teardown(FAILED);
	}

	private void closeReader() {
		try {
			LOGGER.log(Level.FINE, "Closing record reader");
			recordReader.close();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Unable to close record reader", e);
		}
	}

	private void closeWriter() {
		try {
			LOGGER.log(Level.FINE, "Closing record writer");
			recordWriter.close();
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Unable to close record writer", e);
		}
	}

	public void setRecordReader(RecordReader recordReader) {
		this.recordReader = recordReader;
	}

	public void setRecordWriter(RecordWriter recordWriter) {
		this.recordWriter = recordWriter;
	}

	@SuppressWarnings("rawtypes")
	public void addRecordPreProcessor(RecordPreProcessor recordPreProcessor) {
		((CompositeRecordPreProcessor) this.recordPreProcessor).addRecordProcessor(recordPreProcessor);
	}

	@SuppressWarnings("rawtypes")
	public void addRecordProcessor(RecordProcessor recordProcessor) {
		((CompositeRecordProcessor) this.recordProcessor).addRecordProcessor(recordProcessor);
	}

	public void setName(String name) {
		this.name = name;
	}
}