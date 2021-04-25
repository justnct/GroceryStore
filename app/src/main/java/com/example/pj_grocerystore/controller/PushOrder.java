package com.example.pj_grocerystore.controller;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pj_grocerystore.activity.LogInActivity;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.ListProductOfCustomer;
import com.example.pj_grocerystore.model.Product;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PushOrder {
    public static void push(Context context, int number, DetailsProduct product) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TransctionHistory");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.child(DataLocalManager.getString("Username")).getChildrenCount() + 1;
                databaseReference.child(DataLocalManager.getString("Username")).child(String.valueOf(count)).child("Product " + (number + 1)).setValue(product);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
