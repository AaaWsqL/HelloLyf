package com.chotabheem.android.hellolyf.HelperUI;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.chotabheem.android.hellolyf.Adapters.NotificationAdapter;
import com.chotabheem.android.hellolyf.DataModels.DTO_Notification;
import com.chotabheem.android.hellolyf.R;

import java.util.ArrayList;

/**
 * Created by chota_bheem on 13/8/16.
 */
public class NotificationDialog extends DialogFragment {

    private ViewGroup rootView;
    Point p;

    public NotificationDialog() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static NotificationDialog newInstance() {
        NotificationDialog frag =  new NotificationDialog();
        return frag;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.dilalog_notification_listview, container);


        Window window = getDialog().getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//        window.setGravity(Gravity.TOP|Gravity.RIGHT);


        populateNotificationsList();

        // after that, setting values for x and y works "naturally"
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity= Gravity.TOP|Gravity.RIGHT;
        params.y = 100;
        params.x = -40;
        window.setAttributes(params);


        return rootView;
    }


    private void populateNotificationsList() {
        ArrayList<DTO_Notification> arrayOfNotifications = DTO_Notification.getNotifications();

        // Create the adapter to convert the array to views
        NotificationAdapter adapter = new NotificationAdapter(this.getContext(), arrayOfNotifications);

        // Attach the adapter to a ListView
        ListView listView = (ListView) rootView.findViewById(R.id.notification_list);
        listView.setAdapter(adapter);

    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        // Fetch arguments from bundle and set title
//    }

}
