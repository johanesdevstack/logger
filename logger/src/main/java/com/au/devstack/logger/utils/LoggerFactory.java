package com.au.devstack.logger.utils;

import android.content.Context;

import androidx.annotation.Nullable;

import com.au.devstack.logger.Logging;
import com.au.devstack.logger.model.Logger;
import com.au.devstack.logger.model.LoggerConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoggerFactory {

    private Context _context;
    private List<Logger> _logs = new ArrayList<>();

    public LoggerFactory(Context context) {
        _context = context;
    }

    public LoggerConfig getDefaultConfig() {
        LoggerConfig config = new LoggerConfig();
        config.TAG = Logging.TAG;
        config.FORMAT_DATE = Logging.FORMAT_DATE;
        config.SEPARATOR = Logging.SEPARATOR;
        return config;
    }

    public Logger createLogger(LoggerConfig config) {
        Logger log = new Logger(_context, config);
        _logs.add(log);
        return log;
    }

    @Nullable
    public File createLogFile(String tag, String fileName) throws IOException {
        if (tag == null || tag.isEmpty()) return null;

        final Logger logger = getLogger(tag);
        if (logger == null) return null;

        File[] listFiles = FileUtil.searchLogFiles(_context, logger.getConfig().getPREFIX(), logger.getConfig().getEXTENSION());
        if (listFiles == null) return null;

        return FileUtil.zip(_context, listFiles, fileName);
    }

    public void dispose() {
        for (Logger log : _logs) {
            log.stop();
        }
    }

    private Logger getLogger(String tag) {
        for (Logger log : _logs) {
            if (log.getConfig().getTAG().equals(tag))
                return log;
        }
        return null;
    }
}
