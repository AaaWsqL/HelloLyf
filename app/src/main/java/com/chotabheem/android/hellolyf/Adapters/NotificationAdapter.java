package com.chotabheem.android.hellolyf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chotabheem.android.hellolyf.DataModels.DTO_Notification;
import com.chotabheem.android.hellolyf.R;

import java.util.ArrayList;

/**
 * Created by chota_bheem on 13/8/16.
 */
public class NotificationAdapter extends ArrayAdapter<DTO_Notification> {
    public NotificationAdapter(Context context, ArrayList<DTO_Notification> notifications){
        super(context, 0, notifications);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DTO_Notification notification = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notification_row, parent, false);
        }

        // Lookup view for data population
        TextView tvDate = (TextView) convertView.findViewById(R.id.firstLine);
        TextView tvMessage = (TextView) convertView.findViewById(R.id.secondLine);

        // Populate the data into the template view using the data object
        tvDate.setText(notification.getDate());
        tvMessage.setText(notification.getMessage());

        // Return the completed view to render on screen
        return convertView;
    }
}
