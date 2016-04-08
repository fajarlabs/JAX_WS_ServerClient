package com.skck.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * 
 * @author masfajar
 *
 */
public class AppLog {
	private final static String LOG_ERROR = "logs/error_log_desktop.txt";
	private final static String LOG_TIME = "logs/start_log_desktop.txt";
	/**
	 * Untul Logger Error Aplikasi
	 * @return
	 */
	public static Logger error() {
		Logger logger = Logger.getLogger("ErrorLogDesktop");
		FileHandler fh;

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler(LOG_ERROR);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return logger;
	}

	/**
	 * Untuk timestamp user Start Stop Control Panel
	 * @return
	 */
	public static Logger time() {
		Logger logger = Logger.getLogger("ErrorLogDesktop");
		FileHandler fh;

		try {
			// This block configure the logger with handler and formatter
			fh = new FileHandler(LOG_TIME);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return logger;
	}
}
