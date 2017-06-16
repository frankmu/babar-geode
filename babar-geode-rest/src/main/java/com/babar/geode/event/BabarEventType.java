package com.babar.geode.event;

public enum BabarEventType {
	SYSLOG("syslog"), TRAP("trap");

	private final String type;

	private BabarEventType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return type;
	}
}
