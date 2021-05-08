package com.example.pj_grocerystore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ListViewProductHistoryTransAdapter;
import com.example.pj_grocerystore.adapter.SpinnerAdapter;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.getListHistoryTrans;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowHistoryTrans extends AppCompatActivity {
    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;
    private Context context = this;
    private ListView listView;
    private ListViewProductHistoryTransAdapter listViewProductHistoryTransAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history_trans);
        addUI();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("TransctionHistory")
                        .child(DataLocalManager.getString("Username"))
                        .child(spinnerAdapter.getItem(position).getName());
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
                        listViewProductHistoryTransAdapter = new ListViewProductHistoryTransAdapter(context, R.layout.custom_listview_show_history_trans,list);
                        listView.setAdapter(listViewProductHistoryTransAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addUI() {
        spinner = findViewById(R.id.spinner);
        spinnerAdapter = new SpinnerAdapter(this, R.layout.item_select, getListHistoryTrans.getListTimeOrder(context));
        spinner.setAdapter(spinnerAdapter);
        listView = findViewById(R.id.show_list_history_trans);
    }


}