package com.example.pj_grocerystore.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ListProductOfCustomerAdapter;
import com.example.pj_grocerystore.controller.PushNotification;
import com.example.pj_grocerystore.controller.PushOrder;
import com.example.pj_grocerystore.model.ChannelNotification;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.Internet;
import com.example.pj_grocerystore.model.ListProductOfCustomer;

import java.util.ArrayList;
import java.util.Date;

public class CartFragment extends Fragment {
    private ListView lv_item_customer;
    private ListProductOfCustomerAdapter listProductOfCustomerAdapter;
    private Button btn_pay;

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

        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ListProductOfCustomer.getDetailsProductList().isEmpty()) {
                    if (Internet.isNetworkAvailable(getContext())) {
                        for (int i = 0; i < ListProductOfCustomer.getDetailsProductList().size(); i++) {
                            DetailsProduct detailsProduct = ListProductOfCustomer.getDetailsProductList().get(i);
                            PushOrder.push(getContext(), i, detailsProduct);
                        }
                        //send notification
                        String title1 = "ORDER SUCCESS";
                        String title2 = "We will bring food for u !";
                        PushNotification.PushNotificationInFragment(getResources(), getContext(), getActivity(), title1, title2);
                        ListProductOfCustomer.cleanProduct();
                        listProductOfCustomerAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getActivity(), "You are need Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        return view;
    }

    private void addUI(View view) {
        lv_item_customer = view.findViewById(R.id.list_item_customer);
        btn_pay = view.findViewById(R.id.pay);
        listProductOfCustomerAdapter = new ListProductOfCustomerAdapter(getContext(), R.layout.custom_list_cart, ListProductOfCustomer.getDetailsProductList());
    }

}