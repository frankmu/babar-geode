package com.cviz.geode.pipeline.task;

import java.util.concurrent.Callable;

public interface Task extends Callable<TaskExecutionResult> {

    String getName();

    @Override
    TaskExecutionResult call();
}