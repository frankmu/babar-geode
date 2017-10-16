package com.cviz.geode.pipeline.task;

class CvizPipelineException extends Exception {

	private static final long serialVersionUID = -6668656089584290917L;

	CvizPipelineException(String s, Exception e) {
        super(s, e);
    }
}