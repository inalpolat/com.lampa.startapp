/**
	com.lampa2.startapp, ver. 6.1.6
	https://github.com/inalpolat/com.lampa2.startapp
	
	Phonegap plugin for check or launch other application in android device (iOS support).
	bug tracker: https://github.com/inalpolat/com.lampa2.startapp/issues
*/

package com.lampa2.startapp;

import android.content.Intent;
import android.util.Log;

import org.apache.cordova.CordovaPlugin;

import java.lang.reflect.Field;
import java.util.regex.Pattern;


public class Assets extends CordovaPlugin {
    protected static final String TAG = "startApp";
    protected boolean NO_PARSE_INTENT_VALS = false;


    /**
     * functions
     */
    protected String parseExtraName(String extraName) {
        String parseIntentExtra = extraName;

        try {
            parseIntentExtra = getIntentValueString(extraName);
        }
        catch(NoSuchFieldException e) {
            parseIntentExtra = extraName;
        }
        catch(IllegalAccessException e) {
            e.printStackTrace();
            return extraName;
        }

        Log.e(TAG, parseIntentExtra);

        return parseIntentExtra;
    }

    protected String getIntentValueString(String flag) throws NoSuchFieldException, IllegalAccessException {

        if(NO_PARSE_INTENT_VALS) {
            return flag;
        }

        Field field = Intent.class.getDeclaredField(flag);
        field.setAccessible(true);

        return (String) field.get(null);
    }

    protected int getIntentValue(String flag) throws NoSuchFieldException, IllegalAccessException {
        Field field = Intent.class.getDeclaredField(flag);
        field.setAccessible(true);

        return field.getInt(null);
    }

    protected boolean matchDoubleInString(String str) {
        return (Pattern.matches("([0-9]*)\\.([0-9]*)", str));
    }
}
