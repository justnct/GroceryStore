package com.example.pj_grocerystore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.MainActivity;


public class Tutorial_2 extends Fragment {
    private Button btnGetStart;

    public Tutorial_2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutorial_2, container, false);
        btnGetStart = view.findViewById(R.id.getStartAtTutorial);
        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(i);
                getActivity().finish();
            }
        });
        return view;
    }
}