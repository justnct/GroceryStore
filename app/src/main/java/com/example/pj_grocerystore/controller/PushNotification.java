package com.example.pj_grocerystore.controller;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.ChannelNotification;

import java.util.Date;

public class PushNotification {

    //push notification infragment
    public static void PushNotificationInFragment(Resources resources, Context context, FragmentActivity fragmentActivity, String title1, String title2){
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round);
        Notification notification = new NotificationCompat.Builder(context, ChannelNotification.CHANNEL_ID)
                .setContentTitle(title1)
                .setContentText(title2)
                .setSmallIcon(R.drawable.ic_store)
                .setLargeIcon(bitmap)
                .build();
        NotificationManager notificationManager = (NotificationManager) fragmentActivity.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            notificationManager.notify(getNotificationID(), notification);
        }
    }

    //push notification activity
    public static void PushNotificationInActivity(Resources resources, Context context, Activity activity, String title1, String title2){
        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round);
        Notification notification = new NotificationCompat.Builder(context, ChannelNotification.CHANNEL_ID)
                .setContentTitle(title1)
                .setContentText(title2)
                .setSmallIcon(R.drawable.ic_store)
                .setLargeIcon(bitmap)
                .build();
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null) {
            notificationManager.notify(getNotificationID(), notification);
        }
    }

    private static int getNotificationID() {
        return (int) new Date().getTime();
    }

}
