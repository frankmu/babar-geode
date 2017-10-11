package com.cviz.preprocess.rule;

public enum CVizPreProcessType {
	SYSLOG("syslog"), TRAP("trap");

	private final String type;

	private CVizPreProcessType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
