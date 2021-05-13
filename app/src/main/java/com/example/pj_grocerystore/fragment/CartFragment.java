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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.RegisterAccountActivity;
import com.example.pj_grocerystore.adapter.ListProductOfCustomerAdapter;
import com.example.pj_grocerystore.adapter.SpinnerAdapter;
import com.example.pj_grocerystore.adapter.SpinnerCityAdapter;
import com.example.pj_grocerystore.controller.PushNotification;
import com.example.pj_grocerystore.controller.PushOrder;
import com.example.pj_grocerystore.model.ChannelNotification;
import com.example.pj_grocerystore.model.City;
import com.example.pj_grocerystore.model.CustomToast;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.FormatNumber;
import com.example.pj_grocerystore.model.Internet;
import com.example.pj_grocerystore.model.ListProductOfCustomer;
import com.example.pj_grocerystore.model.MyBottomSheetDialogFragment;
import com.example.pj_grocerystore.model.TextTime;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CartFragment extends Fragment {
    private ListView lv_item_customer;
    private static ListProductOfCustomerAdapter listProductOfCustomerAdapter;
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
                        openBottomSheetDialog();
                    } else {
                        CustomToast.customToast(getActivity(), "You are need Internet");
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

    private void openBottomSheetDialog() {
        MyBottomSheetDialogFragment myBottomSheetDialogFragment = MyBottomSheetDialogFragment.newInstance();
        assert getFragmentManager() != null;
        myBottomSheetDialogFragment.show(getFragmentManager(), myBottomSheetDialogFragment.getTag());
        myBottomSheetDialogFragment.setCancelable(false);
    }

    public static void setState(){
        listProductOfCustomerAdapter.notifyDataSetChanged();
    }




}