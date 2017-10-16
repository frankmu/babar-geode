package com.cviz.geode.pipeline.task;

import java.io.Serializable;

public class TaskParameters implements Serializable {

	public static final int DEFAULT_BATCH_SIZE = 100;

	private static final long serialVersionUID = 6717170390804796922L;
    private int batchSize;

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }
}