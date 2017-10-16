package com.cviz.geode.pipeline.task;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TaskMetrics implements Serializable {

	private static final long serialVersionUID = 4354818253213280976L;

	private long startTime;
    private long endTime;
    private long readCount;
    private long writeCount;
    private long filteredCount;
    private long errorCount;

    private Map<String, Object> customMetrics = new HashMap<>();

    public void incrementFilteredCount() {
        filteredCount++;
    }

    public void incrementErrorCount() {
        errorCount++;
    }

    public void incrementReadCount() {
        readCount++;
    }

    public void incrementWriteCount(long count) {
        writeCount += count;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return getEndTime() - getStartTime();
    }

    public long getFilteredCount() {
        return filteredCount;
    }

    public long getErrorCount() {
        return errorCount;
    }

    public long getReadCount() {
        return readCount;
    }

    public long getWriteCount() {
        return writeCount;
    }

    public void addMetric(String name, Object value) {
        customMetrics.put(name, value);
    }

    public Map<String, Object> getCustomMetrics() {
        return customMetrics;
    }
}