package com.example.pj_grocerystore.model;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;

public class CustomToast {
    public static void customToast(Activity activity, String mess) {
        Toast toast = new Toast(activity);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast, (ViewGroup) activity.findViewById(R.id.layout_custom_toast));
        TextView tv_messenger = view.findViewById(R.id.messenger_toast);
        tv_messenger.setText(mess);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
