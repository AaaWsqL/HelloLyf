package com.chotabheem.android.hellolyf.DataModels;

import java.util.ArrayList;

/**
 * Created by chota_bheem on 13/8/16.
 */
public class DTO_Notification {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String date;
    private String message;

    public DTO_Notification(String date, String message) {
        this.date=date;
        this.message = message;
    }

    public static ArrayList<DTO_Notification> getNotifications() {
        ArrayList<DTO_Notification> mNotifications  = new ArrayList<DTO_Notification>();

        mNotifications.add(new DTO_Notification( "Appointment","With Dr. Soumik Debnath"));
        mNotifications.add(new DTO_Notification("Appointment","With Dr. Soumik Debnath"));
        mNotifications.add(new DTO_Notification("Appointment","With Dr. Soumik Debnath"));

        return mNotifications;
    }
}
