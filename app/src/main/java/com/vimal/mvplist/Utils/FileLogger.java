package com.vimal.mvplist.Utils;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.util.SparseArray;


import com.vimal.mvplist.BuildConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * To save the logs to File
 * Created by vimal.
 */

public class FileLogger {
    private final static String FOLDER_NAME = "log";
    private final static String FILE_NAME = "App.log";
    private final static String LOG_CLASS = "= ";
    private static FileLogger ourInstance = new FileLogger();
    private final SparseArray<ArrayList<String>> loggersSparse = new SparseArray<>();
    private File myExternalFile;
    private WeakReference<Application> application;


    public static FileLogger getInstance() {
        if (ourInstance == null) {
            ourInstance = new FileLogger();

        }
        return ourInstance;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }

    /**
     * Start logging the data. call super.OnCreate() in onCreate() method;
     *
     * @param Tag Unique class name- ex: Current activity class name
     *            FileLoggerActivity.class.getSimpleName()
     */
    public void startLoggerForTag(String Tag) {
        appendLog(Tag, "StartTime:" + new Date().toString());
    }

    /**
     * Call this in <MainApplication >extends Application class<br/>
     * FileLogger.getInstance().attachApplication(this);
     *
     * @param application application instance
     */
    public void attachApplication(Application application) {
        if (BuildConfig.isShowLog) {
            this.application = new WeakReference<Application>(application);
            if (myExternalFile == null) {
                createFile();
            } else {
                if (!myExternalFile.exists()) {
                    createFile();
                } else {
                    myExternalFile.delete();
                    createFile();
                }
            }
        }
    }

    /**
     * .
     * Write the information to file
     *
     * @param tag activity name
     */
    public synchronized void flush(String tag) {
        if (BuildConfig.isShowLog) {
            if (application != null && application.get() != null) {
                if (isExternalStorageAvailable()) {
                    try {
                        appendLog(tag, "endTime:" + new Date().toString());
                        Logger.e("File Path", myExternalFile.getAbsolutePath());
                        FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                        OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
                        ArrayList<String> appendList = getList(tag);
                        for (String s : appendList) {
                            myOutWriter.append(s);
                            myOutWriter.append("\n\r");
                        }
                        myOutWriter.close();
                        fos.close();
                        appendList.clear();
                        loggersSparse.remove(tag.hashCode());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Append the log and finally flush to write to file
     *
     * @param log
     */
    public synchronized void appendLog(String tag, String log) throws RuntimeException {
        if (BuildConfig.isShowLog) {
            if (application == null) {
                throw new RuntimeException("call attachApplication(Application ) in Application class");
            }
            ArrayList<String> appendList = getList(tag);
            if (appendList != null) {
                appendList.add(tag + LOG_CLASS + log);
            }
        }
    }

    private ArrayList<String> getList(String tag) {
        int hasCode = tag.hashCode();
        ArrayList<String> appendList = loggersSparse.get(hasCode);
        if (appendList == null) {
            appendList = new ArrayList<>();
            Collections.synchronizedList(appendList);
            loggersSparse.put(hasCode, appendList);
        }
        return appendList;
    }

    private void createFile() {
        if (isExternalStorageAvailable() && application.get() != null) {
            myExternalFile = new File(application.get().getApplicationContext().getExternalFilesDir(FOLDER_NAME), FILE_NAME);
            writeDeviceInfo();
        }
    }

    private void writeDeviceInfo() {
        if (myExternalFile != null) {
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(myExternalFile, false);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
                myOutWriter.append(Build.DEVICE).append(" , ").append(Build.MODEL);
                myOutWriter.append("\n\r");
                myOutWriter.close();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
