package com.vimal.mvplist.Utils;

public interface CallBack<T> {
    /**
     * Success
     *
     * @param objects - Model object
     */
    void success(T objects);

    void responseFailure(T objects);

    void connectionFailure(Throwable errorThrowable);
}
