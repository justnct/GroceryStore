package com.example.pj_grocerystore.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ListProductOfCustomerAdapter;
import com.example.pj_grocerystore.model.ListProductOfCustomer;

public class CartFragment extends Fragment {
    private ListView lv_item_customer;
    public static ListProductOfCustomerAdapter listProductOfCustomerAdapter;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        addUI(view);
        lv_item_customer.setAdapter(listProductOfCustomerAdapter);



        return view;
    }

    private void addUI(View view) {
        lv_item_customer = view.findViewById(R.id.list_item_customer);
        listProductOfCustomerAdapter = new ListProductOfCustomerAdapter(getContext(),R.layout.custom_list_cart, ListProductOfCustomer.getDetailsProductList());
    }

}