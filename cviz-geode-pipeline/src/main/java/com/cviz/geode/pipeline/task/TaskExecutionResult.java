package com.cviz.geode.pipeline.task;

import java.io.Serializable;

public class TaskExecutionResult implements Serializable {

	private static final long serialVersionUID = 7573896254804136259L;

    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status.toString();
    }
}