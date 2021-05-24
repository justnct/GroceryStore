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
        if (!Internet.isNetworkAvailable(context)) {
            //no internet
            if (!DataLocalManager.checkExitst("Account")) { //not logged in
                Intent intent1 = new Intent(context, LogInActivity.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent1);
            }
        } else {
            //have internet
            if (DataLocalManager.checkExitst("Account")) { //logged
                intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {    //not logged in
                intent = new Intent(context, LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
