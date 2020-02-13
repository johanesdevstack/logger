package com.au.devstack.logger.utils;

import android.content.Context;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    private static final String DIRECTORY_LOGS = "logs";
    private static final int BUFFER = 1024;

    public static final File createLogFile(Context context, String fileName, String separator, String extension, boolean unique) throws IOException {
        // Create an image file name
        File storageDir = context.getExternalFilesDir(DIRECTORY_LOGS);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        fileName = fileName == null || fileName.isEmpty() ? "LOG_" + timeStamp : fileName;
        if (unique)
            return File.createTempFile(
                    fileName + separator,  /* prefix */
                    extension,         /* suffix */
                    storageDir      /* directory */
            );
        return new File(storageDir.getCanonicalPath() + File.separator + fileName + extension);
    }

    @Nullable
    public static final File getLogDirectory(Context context) {
        return context.getExternalFilesDir(FileUtil.DIRECTORY_LOGS);
    }

    @Nullable
    public static final File[] searchLogFiles(Context context, final String prefix, final String extension) {
        File logDirectory = getLogDirectory(context);

        if (logDirectory == null) return null;

        return logDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                boolean prefixMatched = (prefix == null || prefix.isEmpty()) || file.getName().startsWith(prefix);
                boolean extensionMatched = (extension == null || extension.isEmpty()) || file.getName().endsWith(extension);
                return prefixMatched && extensionMatched;
            }
        });
    }

    public static final File createZipFile(Context context, String fileName) throws IOException {
        // Create an image file name
        File storageDir = context.getExternalFilesDir(DIRECTORY_LOGS);
        String extension = ".zip";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        fileName = fileName == null || fileName.isEmpty() ? "LOG_" + timeStamp : fileName;
        return new File(storageDir.getCanonicalPath() + File.separator + fileName + extension);
    }

    @Nullable
    public static final File zip(Context context, File[] _files, String zipFileName) throws IOException {

        File result = FileUtil.createZipFile(context, zipFileName);
        if (!result.createNewFile())
            if (result.delete())
                result = FileUtil.createZipFile(context, zipFileName);
            else
                return null;

        BufferedInputStream origin;
        FileOutputStream dest = new FileOutputStream(result.getAbsolutePath());
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                dest));
        byte[] data = new byte[BUFFER];

        for (int i = 0; i < _files.length; i++) {
            FileInputStream fi = new FileInputStream(_files[i]);
            origin = new BufferedInputStream(fi, BUFFER);

            ZipEntry entry = new ZipEntry(_files[i].getName());
            out.putNextEntry(entry);
            int count;

            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            origin.close();
        }

        out.close();

        return result;
    }
}
