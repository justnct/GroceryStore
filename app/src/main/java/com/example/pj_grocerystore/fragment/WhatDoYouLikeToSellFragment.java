package com.example.pj_grocerystore.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.LikeIsSellProductAdapter;
import com.example.pj_grocerystore.model.ProductTest;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class WhatDoYouLikeToSellFragment extends Fragment {
    private RecyclerView recyclerView;
    private LikeIsSellProductAdapter likeIsSellProductAdapter;

    public WhatDoYouLikeToSellFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_what_do_you_like_to_sell, container, false);
        addUI(view);
        likeIsSellProductAdapter = new LikeIsSellProductAdapter(DataLocalManager.getListProductTEST("mListProduct"),
                DataLocalManager.getListBitmap("mListBitmap"));
        recyclerView.setAdapter(likeIsSellProductAdapter);

        return view;
    }

    private void addUI(View view) {
        recyclerView = view.findViewById(R.id.rv_likeIsSell);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }



}