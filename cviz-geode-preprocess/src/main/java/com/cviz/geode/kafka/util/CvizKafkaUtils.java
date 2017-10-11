package com.cviz.geode.kafka.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class CvizKafkaUtils {

	public static Long getFormattedTimestamp(String timestamp, String pattern) {
		try {
			SimpleDateFormat dt = new SimpleDateFormat(pattern);
			Date date = dt.parse(timestamp);
			if (!pattern.contains("yyyy")) {
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
				date = c.getTime();
			}
			return date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
