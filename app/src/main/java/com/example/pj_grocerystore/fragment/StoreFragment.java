package com.example.pj_grocerystore.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.RadioGroup;


import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ProductAdapter;
import com.example.pj_grocerystore.model.ListData;
import com.example.pj_grocerystore.model.Product;

import java.util.ArrayList;
import java.util.Objects;


public class StoreFragment extends Fragment {
    private RadioGroup radioGroup;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private EditText input_Search;
    private int type = 0;
    private ImageView img_deleteInputSearch;
    private Intent intent = null;

    public StoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        addUI(view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        productAdapter = new ProductAdapter(ListData.getListData(0));
        recyclerView.setAdapter(productAdapter);


        if ((intent = Objects.requireNonNull(getActivity()).getIntent()) != null) {
            int current = intent.getIntExtra("currentProduct",0);
            gridLayoutManager.scrollToPositionWithOffset(current,0);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btnAll:
                        productAdapter = new ProductAdapter(ListData.getListData(0));
                        type = 0;
                        break;
                    case R.id.btnVegetables:
                        productAdapter = new ProductAdapter(ListData.getListData(1));
                        type = 1;
                        break;
                    case R.id.btnMeat:
                        productAdapter = new ProductAdapter(ListData.getListData(2));
                        type = 2;
                        break;
                    case R.id.btnFlower:
                        productAdapter = new ProductAdapter(ListData.getListData(3));
                        type = 3;
                        break;
                    default:
                        productAdapter = new ProductAdapter(ListData.getListData(0));
                        type = 0;
                        break;
                }

                recyclerView.setAdapter(productAdapter);
                filter(input_Search.getText().toString());
            }
        });


        input_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        img_deleteInputSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_Search.setText(null);
            }
        });

        return view;
    }

    private void filter(String text) {
        ArrayList<Product> listSearch = new ArrayList<>();
        for (Product result : ListData.getListData(type)) {
            if (result.getName().toLowerCase().contains(text.toLowerCase())) {
                listSearch.add(result);
            }
        }
        productAdapter.filterList(listSearch);
    }


    private void addUI(View view) {
        radioGroup = view.findViewById(R.id.radioGroup);
//        rb_All = view.findViewById(R.id.btnAll);
//        rb_Vege = view.findViewById(R.id.btnVegetables);
//        rb_Meat = view.findViewById(R.id.btnMeat);
//        rb_Flo = view.findViewById(R.id.btnFlower);
        recyclerView = view.findViewById(R.id.rcv_item);
        productAdapter = new ProductAdapter(ListData.getListData(0));
        input_Search = view.findViewById(R.id.input_search);
        img_deleteInputSearch = view.findViewById(R.id.delete_tvSearch);
    }

}