package com.example.pj_grocerystore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;


import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ListViewProductHistoryTransAdapter;
import com.example.pj_grocerystore.adapter.SpinnerAdapter;
import com.example.pj_grocerystore.model.getListHistoryTrans;

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
//        listViewProductHistoryTransAdapter = new ListViewProductHistoryTransAdapter(context, R.layout.custom_listview_show_history_trans,);
    }


}