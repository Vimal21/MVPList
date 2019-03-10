package com.vimal.mvplist;

/**
 * Created by vimal
 */
public interface BasePresenter {

    /**
     * Called from View to start the first scenario
     */
    void start();

    /**
     * File Logger to save the log in file
     *
     * @param log
     */
    void Filelogger(String log);

    /**
     * Copy the saved log to file
     * Should call on onDestroy method
     */
    void FlushLogger();
}
