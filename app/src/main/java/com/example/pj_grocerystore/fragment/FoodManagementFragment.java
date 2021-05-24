package com.example.pj_grocerystore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.AddNewFoodForm;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FoodManagementFragment extends Fragment {
    private ListView listNameFood;
    private Button btnAddFood;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_management, container, false);
        addUI(view);
        addAction();
        return view;
    }

    private void addAction() {
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //register new food
                Intent i = new Intent(getActivity(), AddNewFoodForm.class);
                startActivity(i);
            }
        });
    }

    private void addUI(View view) {
        listNameFood = view.findViewById(R.id.list_food);
        btnAddFood = view.findViewById(R.id.add_new_food_admin);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getListName());
        listNameFood.setAdapter(arrayAdapter);
    }

    public ArrayList<String> getListName() {
        ArrayList<String> list = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Food");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    list.add(dataSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return list;
    }
}