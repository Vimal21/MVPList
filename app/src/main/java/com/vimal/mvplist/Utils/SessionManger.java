package com.vimal.mvplist.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.ArrayMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Holds all session values
 * Created by Vimal.
 */

public class SessionManger {
    private SharedPreferences mPreferences;
    private static final String SHARED_PREFERENCE_FILE = "KEY";

    /**
     * Initiate Session manager
     *
     * @param context current context
     */
    public SessionManger(Context context) {
        mPreferences = context.getSharedPreferences(SHARED_PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    /**
     * add/ update string value
     *
     * @param key   identification
     * @param value value for the key
     */
    public void setStringKey(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Bulk Input string
     *
     * @param bulkUpdate bulk key- value pair
     */
    public void setStringKey(ArrayMap<String, String> bulkUpdate) {
        SharedPreferences.Editor editor = mPreferences.edit();
        Set<String> keys = bulkUpdate.keySet();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            editor.putString(key, bulkUpdate.get(key));
            editor.apply();
        }
    }

    /**
     * add/ update Boolean value
     *
     * @param key   identification
     * @param value value for the key
     */
    public void setBooleanKey(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * add/ update Int value
     *
     * @param key   identification
     * @param value value for the key
     */
    public void setIntKey(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Store the Array of strings
     *
     * @param key         Name of the key to store
     * @param stringArray arrays to store
     */
    public void setStringArray(String key, ArrayList<String> stringArray) {
        if (stringArray == null || stringArray.size() == 0) {
            return;
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        Set<String> strings = new HashSet<>(stringArray);
        editor.putStringSet(key, strings);
        editor.apply();
    }

    /**
     * get the array strings to corresponding key
     *
     * @param key key which you need it
     * @return @{@link ArrayList}
     */
    public ArrayList<String> getStringArray(String key) {
        return new ArrayList<String>(mPreferences.getStringSet(key, new HashSet<String>()));
    }

    /**
     * Get the session value (Int)
     *
     * @param key Required value
     * @return if key has value or 0 by default
     */
    public int getIntKey(String key) {
        try{
            return mPreferences.getInt(key, 0);
        }catch (Exception e){
            return 0;
        }
    }

    /**
     * Get the session value (Boolean)
     *
     * @param key Required value
     * @return if key has value or false by default
     */
    public boolean getBooleanKey(String key) {
        return mPreferences.getBoolean(key, false);
    }

    /**
     * Get the session value (Boolean) referral code
     *
     * @param key Required value
     * @return if key has value or true by default
     */
    public boolean getBooleanReferralKey(String key) {
        return mPreferences.getBoolean(key, true);
    }

    /**
     * Get the session value (String)
     *
     * @param key Required value
     * @return if key has value or empty by default
     */

    public String getStringKey(String key) {
        return mPreferences.getString(key, "");
    }

    /**
     * Clear the session <br/>
     * Expect LANGUAGE_COUNTRY,LANGUAGE_FONT,LANGUAGE_LOCALE
     */
    public void clearSession() {
        mPreferences.edit().clear().apply();
    }
}
