package com.au.devstack.logger;

import android.content.Context;
import android.util.Log;

import com.au.devstack.logger.model.Logger;
import com.au.devstack.logger.utils.LoggerFactory;

import java.util.Arrays;
import java.util.Calendar;

public abstract class Logging {

    public static final String FORMAT_DATE = "yyyyMMdd_HHmmss";

    public static final int EXPIRY_BY_SECOND = Calendar.SECOND;
    public static final int EXPIRY_BY_MINUTE = Calendar.MINUTE;
    public static final int EXPIRY_BY_DATE = Calendar.DATE;
    public static final int EXPIRY_BY_MONTH = Calendar.MONTH;

    public static final String TAG = "LOG";
    public static final String SEPARATOR = "_";
    public static final int EXPIRY = 3;
    public static final String EXTENSION = ".log";

    private static final String CHILD_SEPARATOR = "->";

    private static LoggerFactory _factory;

    public static void init(Context context) {
        _factory = new LoggerFactory(context);
    }

    public static LoggerFactory Builder() {
        return _factory;
    }

    public static void stopAll() {
        _factory.dispose();
    }

    // DEBUG LEVEL
    public static void d(String tag, Class aClass, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.d(tag, String.format("%s %s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void d(String tag, Class aClass, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.d(tag, String.format("%s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // ERROR LEVEL
    public static void e(String tag, Class aClass, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.e(tag, String.format("%s %s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void e(String tag, Class aClass, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.e(tag, String.format("%s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // INFO LEVEL
    public static void i(String tag, Class aClass, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.i(tag, String.format("%s %s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void i(String tag, Class aClass, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.i(tag, String.format("%s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // VERBOSE LEVEL
    public static void v(String tag, Class aClass, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.v(tag, String.format("%s %s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void v(String tag, Class aClass, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.v(tag, String.format("%s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // WARNING LEVEL
    public static void w(String tag, Class aClass, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.w(tag, String.format("%s %s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void w(String tag, Class aClass, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.w(tag, String.format("%s %s %s", aClass.getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }


    private static String getFormattedStackTrace() {
        final StackTraceElement[] stacktraces = Thread.currentThread().getStackTrace();
        StackTraceElement stackElement = null;
        boolean found = false;
        for (int x = 0; x < stacktraces.length; x++) {
            if (stacktraces[x].getClassName().equals(Logging.class.getName()) || stacktraces[x].getClassName().equals(Logger.class.getName())) {
                found = true;
            } else if (found) {
                stackElement = stacktraces[x];
                break;
            }
        }
        return stackElement == null ? "" : String.format("%s:%s:%s %s", stackElement.getFileName(), stackElement.getMethodName(), stackElement.getLineNumber(), CHILD_SEPARATOR);
    }

    private static String getFormattedStackTrace(Throwable e) {
        return String.format("%s Stacktrace : %s", e.toString(), Arrays.toString(e.getStackTrace()));
    }
}
