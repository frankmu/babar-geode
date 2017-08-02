package com.cviz.geode.rule;

public enum CVizEventType {
	SYSLOG("syslog"), TRAP("trap");

	private final String type;

	private CVizEventType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
