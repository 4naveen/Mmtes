package com.project.lorvent.mmtes.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AppSession {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "Favourite";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    public static boolean isFirstTime=false;
    public AppSession(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
