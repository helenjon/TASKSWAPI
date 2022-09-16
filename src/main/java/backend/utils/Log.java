package backend.utils;

import backend.utils.helpers.ClassHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public Log() {
    }

    public static synchronized void info(Object message) {
        String name = ClassHelper.getCallerClassName();
        Logger log = getLog(name);
        log.info(message);
    }

    public static synchronized void error(Object message) {
        String name = ClassHelper.getCallerClassName();
        Logger log = getLog(name);
        log.error(message);
    }

    public static synchronized void warn(Object message) {
        String name = ClassHelper.getCallerClassName();
        Logger log = getLog(name);
        log.warn(message);
    }

    public static synchronized void debug(String message) {
        String name = ClassHelper.getCallerClassName();
        Logger log = getLog(name);
        log.debug(message);
    }

    private static Logger getLog(String name) {
        return LogManager.getLogger(name);
    }
}
