package com.example.pj_grocerystore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ListChoiceOfInformationFragmentAdapter;
import com.example.pj_grocerystore.model.ListChoiceInformationFragment;


public class InformationFragment extends Fragment {
    private TextView tv_username, tv_email;
    private ListChoiceOfInformationFragmentAdapter listChoiceOfInformationFragmentAdapter;
    private ListView lv_choice;
    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        addUI(view);
        SharedPreferences sharedPref = getContext().getSharedPreferences("Account", Context.MODE_PRIVATE);
        tv_username.setText("Hi," + sharedPref.getString("Username",""));
        tv_email.setText(sharedPref.getString("Email",""));

        return view;
    }

    private void addUI(View view) {
        tv_username = view.findViewById(R.id.username_information);
        tv_email = view.findViewById(R.id.email_information);
        lv_choice = view.findViewById(R.id.list_something);
        listChoiceOfInformationFragmentAdapter = new ListChoiceOfInformationFragmentAdapter(getContext(), ListChoiceInformationFragment.getListChoice(),R.layout.custom_listview_fragment_information);
        lv_choice.setAdapter(listChoiceOfInformationFragmentAdapter);
    }
}