package spring.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaticVariable {
	public static final String PROPERTIES_PATH = "F:\\workspace\\api.test\\src\\persistent.properties";
	public static final String LOG4J_PATH = "F:\\workspace\\api.test\\src\\log4j.properties";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	
	public static String starckTraceToString(Exception ex) {
		String result = ex.toString() + "\n";
		StackTraceElement[] trace = ex.getStackTrace();
		for (int i = 0; i < trace.length; i++) {
			result += trace[i].toString() + "\n";
		}
		return result;
	}
	
	public static String date2String(Date date, String pattern) {
		if (null == date) {
			return null;
		} else {
			if (null == pattern || pattern.trim().isEmpty())
				pattern = StaticVariable.DATE_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	public static Date string2Date(String date, String pattern) throws ParseException {
		if (null == date || date.trim().isEmpty()) {
			return null;
		} else {
			if (null == pattern || pattern.trim().isEmpty())
				pattern = StaticVariable.DATE_PATTERN;
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(date);
		}
	}
}
