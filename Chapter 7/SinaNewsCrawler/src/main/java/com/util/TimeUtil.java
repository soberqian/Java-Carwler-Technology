package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class TimeUtil {
	public static String TimeStampToDate(String timestampString, String formats) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
		return date;
	}
}
