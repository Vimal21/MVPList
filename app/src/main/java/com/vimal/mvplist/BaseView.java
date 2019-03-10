package com.vimal.mvplist;

/**
 * Created by vimal
 */

public interface BaseView<T> {

    /**
     * Set base presenter for set connection to the view
     * @param presenter
     */
    void setPresenter(T presenter);
}
