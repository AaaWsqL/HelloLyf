package com.chotabheem.android.hellolyf.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.chotabheem.android.hellolyf.LoginActivity;

import java.util.HashMap;

/**
 * Created by chota_bheem on 26/8/16.
 */
public class SessionManager {

    SharedPreferences mPreferences;
    SharedPreferences.Editor editor;
    Context _context;

    private static final String PREFERENCES_NAME = "AndroidHivePref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PATIENT_ID="patient_ID";

    //Constructor
    public SessionManager(Context context){
        this._context = context;
        mPreferences = _context.getSharedPreferences(PREFERENCES_NAME, 0);
        editor = mPreferences.edit();
    }

    public void createLoginSession(String name, String email, String patient_ID){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_PATIENT_ID, patient_ID);

        // commit changes
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, mPreferences.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, mPreferences.getString(KEY_EMAIL, null));

        return user;
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return mPreferences.getBoolean(IS_LOGIN, false);
    }
}
