package com.example.pj_grocerystore.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.pj_grocerystore.activity.LogInActivity;
import com.example.pj_grocerystore.activity.MainActivity;
import com.example.pj_grocerystore.model.Internet;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;

public class BR_Internet extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Internet.isNetworkAvailable(context)) {
            if (!DataLocalManager.checkExitst("Username")) {
                intent = new Intent(context, LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } else {
            if (DataLocalManager.checkExitst("Username")) {
                intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                intent = new Intent(context, LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
