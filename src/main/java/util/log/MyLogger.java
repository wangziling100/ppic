package util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class MyLogger{

	final public static String DEBUG = "DEBUG";
	final public static String INFO = "INFO";
	final public static String TRACE = "TRACE";
	final public static String ERROR = "ERROR";
	final public static String ALL = "ALL";

	final public static int OS_Err = 0x01;
	final public static int OS_OUT = 0x02;
	final public static int OS_FILE = 0x03;

	private Marker markerFilter;
	private int outputStyle;
	public Logger coreLogger; // logger for implement of my logger

	public MyLogger(String loggerName) {

		this.coreLogger = LoggerFactory.getLogger(loggerName);
		this.outputStyle = OS_Err;
		markerFilter = null;
	}

	public MyLogger(Class<?> loggerName) {
		this.coreLogger = LoggerFactory.getLogger(loggerName);
		this.outputStyle = OS_Err;
		markerFilter = null;
	}

	public MyLogger(String loggerName, Marker filter) {
		this(loggerName);
		this.markerFilter = filter;

	}

	public MyLogger(Class<?> loggerName, Marker filter) {
		this(loggerName);
		this.markerFilter = filter;
	}

	public void setMarkerFilter(Marker filter) {
		this.markerFilter = filter;
	}

	public void setOutputStyle(int style) {
		this.outputStyle = style;
	}


	public void debug(String log) {

		switch (outputStyle) {
		case OS_Err:
			this.coreLogger.debug(log);
			break;
		case OS_OUT:
			// non-implement
			System.out.println("not implemented");
			break;
		case OS_FILE:
			// non-implement
			System.out.println("not implemented");
			break;

		default:
			break;
		}
	}

	public void debug(Marker marker, String log) {
		if (this.markerFilter == null)
			debug(log);
		else if (this.markerFilter.contains(marker))
			debug(log);
	}

	public void info(String log) {
		switch (outputStyle) {
		case OS_Err:
			this.coreLogger.info(log);
			break;
		case OS_OUT:
			// non-implement
			System.out.println("not implemented");
			break;
		case OS_FILE:
			// non-implement
			System.out.println("not implemented");
			break;

		default:
			break;
		}
	}

	public void info(Marker marker, String log) {
		if (this.markerFilter == null)
			info(log);
		else if (this.markerFilter.contains(marker))
			info(log);

	}

	public void error(String log) {
		switch (outputStyle) {
		case OS_Err:
			this.coreLogger.error(log);
			break;
		case OS_OUT:
			// non-implement
			System.out.println("not implemented");
			break;
		case OS_FILE:
			// non-implement
			System.out.println("not implemented");
			break;

		default:
			break;
		}
	}

	public void error(Marker marker, String log) {
		if (this.markerFilter == null)
			error(log);
		else if (this.markerFilter.contains(marker))
			error(log);

	}

	public void trace(String log) {
		switch (outputStyle) {
		case OS_Err:
			this.coreLogger.trace(log);
			break;
		case OS_OUT:
			// non-implement
			System.out.println("not implemented");
			break;
		case OS_FILE:
			// non-implement
			System.out.println("not implemented");
			break;

		default:
			break;
		}
	}

	public void trace(Marker marker, String log) {
		if (this.markerFilter == null)
			trace(log);
		else if (this.markerFilter.contains(marker))
			trace(log);

	}
	
	public static void setLevel(String level){
		String l = level;
		if(level == MyLogger.ALL) l = MyLogger.TRACE;
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, l);
		
	}
	
	public static void main(String[] args){
		MyLogger.setLevel(MyLogger.ALL);
		MyLogger logger = new MyLogger("test");
		logger.debug("test");
		logger.info("test");
	}

}