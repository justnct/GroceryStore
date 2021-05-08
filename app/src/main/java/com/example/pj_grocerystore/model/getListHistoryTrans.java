package com.example.pj_grocerystore.model;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pj_grocerystore.activity.ProductDetails;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class getListHistoryTrans {
    public static ArrayList<TextTime> getListTimeOrder(Context context){
        ArrayList<TextTime> list = new ArrayList<>();
        ArrayList<DetailsProduct> list2 = new ArrayList<>();
        list.add(new TextTime("Select day"));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TransctionHistory")
                .child(DataLocalManager.getString("Username"));
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //list choice of spinner
                    if(dataSnapshot.getChildrenCount() != 0){
                        list.add(new TextTime(dataSnapshot.getKey()));
                    }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }


    public static ArrayList<DetailsProduct> getListProduct(Context context, String date){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TransctionHistory")
                .child(DataLocalManager.getString("Username"))
                .child(date);
        ArrayList<DetailsProduct> list = new ArrayList<>();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    //list choice of spinner
                    if(dataSnapshot.getChildrenCount() != 0){
                        DetailsProduct detailsProduct = dataSnapshot.getValue(DetailsProduct.class);
                        list.add(detailsProduct);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }
}
