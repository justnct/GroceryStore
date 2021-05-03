package com.example.pj_grocerystore.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.TextTime;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter {

    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_selected);
        TextTime tvSelected = (TextTime) this.getItem(position);
        if(tvSelected != null){
            textView.setText(tvSelected.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_history_trans, parent, false);
        TextView textView = convertView.findViewById(R.id.tv_category);
        TextTime textTime = (TextTime) this.getItem(position);
        if(textTime != null){
            textView.setText(textTime.getName());
        }
        return convertView;
    }
}
