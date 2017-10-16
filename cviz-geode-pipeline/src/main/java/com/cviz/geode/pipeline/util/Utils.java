package com.cviz.geode.pipeline.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public abstract class Utils {

	public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	public static final String DURATION_FORMAT = "%sd %shr %smin %ssec %sms";

	private Utils() {

	}

	public static void checkNotNull(Object argument, String argumentName) {
		if (argument == null) {
			throw new IllegalArgumentException(format("The %s must not be null", argumentName));
		}
	}

	public static String formatTime(long time) {
		return new SimpleDateFormat(DATE_FORMAT).format(new Date(time));
	}

	public static String formatDuration(long duration) {
		return String.format(DURATION_FORMAT, MILLISECONDS.toDays(duration), MILLISECONDS.toHours(duration) % 24,
				MILLISECONDS.toMinutes(duration) % 60, MILLISECONDS.toSeconds(duration) % 60, duration % 1000);
	}
}