package com.cviz.geode.pipeline.task;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.cviz.geode.pipeline.util.Utils;

import static java.lang.Runtime.getRuntime;
import static java.util.concurrent.Executors.newFixedThreadPool;

public class TaskExecutor {

    private ExecutorService executorService;

    public TaskExecutor() {
        executorService = newFixedThreadPool(getRuntime().availableProcessors());
    }

    public TaskExecutor(int nbWorkers) {
        executorService = newFixedThreadPool(nbWorkers);
    }

    public TaskExecutor(ExecutorService executorService) {
        Utils.checkNotNull(executorService, "executor service");
        this.executorService = executorService;
    }

    public TaskExecutionResult execute(Task job) {
        try {
            return executorService.submit(job).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException("Unable to execute job " + job.getName(), e);
        }
    }

    public Future<TaskExecutionResult> submit(Task job) {
        return executorService.submit(job);
    }

    public List<Future<TaskExecutionResult>> submitAll(Task... jobs) {
        return submitAll(Arrays.asList(jobs));
    }

    public List<Future<TaskExecutionResult>> submitAll(List<Task> jobs) {
        try {
            return executorService.invokeAll(jobs);
        } catch (InterruptedException e) {
            throw new RuntimeException("Unable to execute jobs", e);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public void awaitTermination(long timeout, TimeUnit unit) {
        try {
            executorService.awaitTermination(timeout, unit);
        } catch (InterruptedException e) {
            throw new RuntimeException("Job executor was interrupted while waiting");
        }
    }
}