package com.au.devstack.logger.model;

import com.au.devstack.logger.Logging;

public class LoggerConfig {
    public String TAG;
    public String SEPARATOR;
    public String PREFIX;
    public String FILENAME;
    public String FORMAT_DATE;
    public Boolean UNIQUE = true;
    public int EXPIRY = Logging.EXPIRY;
    public int EXPIRY_BY = Logging.EXPIRY_BY_DATE;
    public String EXTENSION = Logging.EXTENSION;

    public String getTAG() {
        if (TAG == null || TAG.isEmpty()) return Logging.TAG;
        return TAG;
    }

    public String getEXTENSION() {
        if (EXTENSION == null || EXTENSION.isEmpty()) return Logging.EXTENSION;
        return EXTENSION;
    }

    public String getSEPARATOR() {
        if (SEPARATOR == null || SEPARATOR.isEmpty()) return Logging.SEPARATOR;
        return SEPARATOR;
    }

    public String getPREFIX() {
        if (PREFIX == null) return getTAG();
        return PREFIX;
    }

    public String getFORMAT_DATE() {
        if (FORMAT_DATE == null || FORMAT_DATE.isEmpty())
            return Logging.FORMAT_DATE;
        return FORMAT_DATE;
    }

    public int getEXPIRY_DAYS() {
        if (EXPIRY < 1)
            return Logging.EXPIRY;
        return EXPIRY;
    }
}
