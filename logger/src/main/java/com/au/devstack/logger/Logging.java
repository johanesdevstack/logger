package com.au.devstack.logger;

import android.content.Context;

import com.au.devstack.logger.utils.LoggerFactory;

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

    private static LoggerFactory _factory;

    public static void init(Context context) {
        _factory = new LoggerFactory(context);
    }

    public static LoggerFactory Builder() {
        return _factory;
    }
}
