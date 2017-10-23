package com.cviz.corrprocess.test;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.cviz.corrprocess.pipeline.CvizCorrInputRecordReader;
import com.cviz.geode.cache.domain.Alert;
import com.cviz.geode.common.pipeline.domain.CvizAlertRecord;
import com.cviz.geode.pipeline.record.Record;
import com.cviz.geode.pipeline.task.CvizTaskBuilder;
import com.cviz.geode.pipeline.task.Task;
import com.cviz.geode.pipeline.task.TaskExecutionResult;
import com.cviz.geode.pipeline.task.TaskExecutor;
import com.cviz.geode.pipeline.task.TaskStatus;

public class CVizCorrProcessTest {

	@SuppressWarnings("rawtypes")
	@Test
	public void testProcessRecord() {
		String alertUID = "Test String";
		Alert alert = new Alert();
		alert.setAlertUID(alertUID);
		List<CvizAlertRecord> cvizAlertRecords = Arrays.asList(new CvizAlertRecord(alert));
		CvizTestRecordWriter writer = new CvizTestRecordWriter();
		Task task = new CvizTaskBuilder()
				.reader(new CvizCorrInputRecordReader(cvizAlertRecords))
				.filter(new CvizTestProcessor())
				.writer(writer)
				.build();

		TaskExecutor taskExecutor = new TaskExecutor();
		TaskExecutionResult result = taskExecutor.execute(task);
		assertEquals("Error executing this task.", result.getStatus(), TaskStatus.COMPLETED);
		List<Record> records = writer.getWriterResult();
		assertEquals("Number of records returned is not correct.", 1, records.size());
		Alert processedAlert = (Alert) records.get(0).getPayload();
		assertEquals("Result after processing is not correct", processedAlert.getAlertUID(), alertUID + alertUID);
		taskExecutor.shutdown();
	}
}