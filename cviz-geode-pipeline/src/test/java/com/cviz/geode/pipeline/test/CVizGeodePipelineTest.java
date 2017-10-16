package com.cviz.geode.pipeline.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.cviz.geode.pipeline.task.CvizTaskBuilder;
import com.cviz.geode.pipeline.task.Task;
import com.cviz.geode.pipeline.task.TaskExecutionResult;
import com.cviz.geode.pipeline.task.TaskExecutor;
import com.cviz.geode.pipeline.task.TaskStatus;

public class CVizGeodePipelineTest {

	@Test
	public void testProcessRecord() {
		TestStringRecordWriter writer = new TestStringRecordWriter();
		Task task = new CvizTaskBuilder()
				.reader(new TestStringRecordReader())
				.filter(new TestEvenRecordFilter())
				.writer(writer)
				.build();

		TaskExecutor taskExecutor = new TaskExecutor();
		TaskExecutionResult result = taskExecutor.execute(task);
		assertEquals("Error executing this task.", result.getStatus(), TaskStatus.COMPLETED);
		assertEquals("The final result should have a size of 5", 5, writer.getWriterResult().size());
		taskExecutor.shutdown();
	}
}