package com.example.pj_grocerystore.controller;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.TextTime;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PushOrder {
    public static void push(Context context, int number, DetailsProduct product) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = simpleDateFormat.format(calendar.getTime());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TransctionHistory");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(DataLocalManager.getAccount("Account").getUsername())
                        .child(date)
                        .child("Product " + (number + 1))
                        .setValue(product);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
