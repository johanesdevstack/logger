package com.au.devstack.logger.model;

import android.content.Context;

import com.au.devstack.logger.Logging;
import com.au.devstack.logger.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Logger {
    private final LoggerConfig _config;
    private Process _proses;
    private final Context _context;

    public Logger(Context context, LoggerConfig _config) {
        this._context = context;
        this._config = _config;
    }

    public void start() throws IOException {
        File logFile = FileUtil.createLogFile(_context, generateFileName(), _config.getSEPARATOR(), _config.getEXTENSION(), _config.UNIQUE);
        Logging.i(_config.getTAG(), getClass(), "Log file : " + logFile.getName() + " created");
        _proses = Runtime.getRuntime().exec(String.format("logcat -f %s %s *:S", logFile, _config.TAG));
        removeExistingLog();
        Logging.i(_config.getTAG(), getClass(), "Logger " + _config.getTAG() + " started");
    }

    public boolean started() {
        return _proses != null;
    }

    public void stop() {
        if (!started()) return;
        _proses.destroy();
        Logging.i(_config.getTAG(), getClass(), "Logger " + _config.getTAG() + " stopped");
    }

    public LoggerConfig getConfig() {
        return _config;
    }

    public void d(Class aClass, String message) {
        Logging.d(_config.getTAG(), aClass, message);
    }

    public void d(Class aClass, Throwable t) {
        Logging.d(_config.getTAG(), aClass, t);
    }

    public void e(Class aClass, String message) {
        Logging.e(_config.getTAG(), aClass, message);
    }

    public void e(Class aClass, Throwable t) {
        Logging.e(_config.getTAG(), aClass, t);
    }

    public void i(Class aClass, String message) {
        Logging.i(_config.getTAG(), aClass, message);
    }

    public void i(Class aClass, Throwable t) {
        Logging.i(_config.getTAG(), aClass, t);
    }

    public void v(Class aClass, String message) {
        Logging.v(_config.getTAG(), aClass, message);
    }

    public void v(Class aClass, Throwable t) {
        Logging.v(_config.getTAG(), aClass, t);
    }

    public void w(Class aClass, String message) {
        Logging.w(_config.getTAG(), aClass, message);
    }

    public void w(Class aClass, Throwable t) {
        Logging.w(_config.getTAG(), aClass, t);
    }

    private String generateFileName() {
        String result = "";
        if (!_config.getPREFIX().isEmpty())
            result += _config.getPREFIX() + _config.getSEPARATOR();

        if (_config.FILENAME == null || _config.FILENAME.isEmpty()) {
            result += new SimpleDateFormat(_config.getFORMAT_DATE(), Locale.UK).format(Calendar.getInstance().getTime());
        } else {
            result += _config.FILENAME;
        }

        return result;
    }

    private void removeExistingLog() {
        Calendar expired = Calendar.getInstance();
        expired.add(_config.EXPIRY_BY, _config.getEXPIRY_DAYS() * -1);
        File[] listFiles = FileUtil.searchLogFiles(_context, _config.getPREFIX(), _config.getEXTENSION());

        if (listFiles == null) return;
        for (File file : listFiles) {
            if (file.lastModified() > expired.getTimeInMillis()) continue;
            if (file.getAbsoluteFile().delete())
                Logging.i(_config.getTAG(), getClass(), "Log file : " + file.getName() + " removed");
        }
    }
}
