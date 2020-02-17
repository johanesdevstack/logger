package com.au.devstack.logger;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

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
    public static void d(String tag, Context context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.d(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void d(String tag, Context context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.d(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    public static void d(String tag, Fragment context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.d(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void d(String tag, Fragment context, Throwable e) {
        String stackTrace = getFormattedStackTrace(e);
        Log.d(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, stackTrace));
    }

    // ERROR LEVEL
    public static void e(String tag, Context context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.e(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void e(String tag, Context context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.e(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    public static void e(String tag, Fragment context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.e(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void e(String tag, Fragment context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.e(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // INFO LEVEL
    public static void i(String tag, Context context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.i(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void i(String tag, Context context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.i(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    public static void i(String tag, Fragment context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.i(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void i(String tag, Fragment context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.i(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // VERBOSE LEVEL
    public static void v(String tag, Context context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.v(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void v(String tag, Context context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.v(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    public static void v(String tag, Fragment context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.v(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void v(String tag, Fragment context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.v(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    // WARNING LEVEL

    public static void w(String tag, Context context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.w(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void w(String tag, Context context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.w(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    public static void w(String tag, Fragment context, String message) {
        String formattedStackTrace = getFormattedStackTrace();
        Log.w(tag, String.format("%s %s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace, message));
    }

    public static void w(String tag, Fragment context, Throwable e) {
        String formattedStackTrace = getFormattedStackTrace(e);
        Log.w(tag, String.format("%s %s %s", context.getClass().getSimpleName(), CHILD_SEPARATOR, formattedStackTrace));
    }

    private static String getFormattedStackTrace() {
        final StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackElement = stacktrace.length > 4 ? stacktrace[4] : null;
        return stackElement == null ? "" : String.format("%s:%s:%s", stackElement.getFileName(), stackElement.getMethodName(), stackElement.getLineNumber()) + " -> ";
    }

    private static String getFormattedStackTrace(Throwable e) {
        return String.format("%s Stacktrace : %s", e.toString(), Arrays.toString(e.getStackTrace()));
    }
}
