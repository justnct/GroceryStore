package com.example.pj_grocerystore.model;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.SpinnerCityAdapter;
import com.example.pj_grocerystore.api.ApiGetListCity;
import com.example.pj_grocerystore.api.ListCity;
import com.example.pj_grocerystore.controller.PushNotification;
import com.example.pj_grocerystore.controller.PushOrder;
import com.example.pj_grocerystore.fragment.CartFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private TextView tv_listNameProduct, tv_totalMoney;
    private Button btnCancel, btnConfirm;
    private Spinner spinnerCity;
    private SpinnerCityAdapter spinnerCityAdapter;

    public static MyBottomSheetDialogFragment newInstance() {
        MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment();
        return myBottomSheetDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_bottom_sheet_confirm_order, null);
        bottomSheetDialog.setContentView(view);
        addUI(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send notification
                for (int i = 0; i < ListProductOfCustomer.getDetailsProductList().size(); i++) {
                    DetailsProduct detailsProduct = ListProductOfCustomer.getDetailsProductList().get(i);
                    PushOrder.push(getContext(), i, detailsProduct);
                }
                String title1 = "ORDER SUCCESS";
                String title2 = "We will bring food for u !";
                PushNotification.PushNotificationInFragment(getResources(), getContext(), getActivity(), title1, title2);
                ListProductOfCustomer.cleanProduct();
                CartFragment.setState();
                bottomSheetDialog.dismiss();
            }
        });

        return bottomSheetDialog;
    }

    private void addUI(View view) {
        btnCancel = view.findViewById(R.id.btn_cancel);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        tv_totalMoney = view.findViewById(R.id.total_money);
        tv_listNameProduct = view.findViewById(R.id.list_name_product);
        spinnerCity = view.findViewById(R.id.spinner_city);
        spinnerCityAdapter = new SpinnerCityAdapter(getContext(), R.layout.item_select, getListCity());
        spinnerCity.setAdapter(spinnerCityAdapter);
        tv_totalMoney.setText(FormatNumber.formatNumberDouble(ListProductOfCustomer.getTotalMoney()));
        tv_listNameProduct.setText(ListProductOfCustomer.getListNameProduct());
    }

    private List getListCity() {

        ArrayList<City> listCity = new ArrayList<>();
        listCity.add(new City("Select City"));
        ApiGetListCity.api.getListCity().enqueue(new Callback<ListCity>() {
            @Override
            public void onResponse(Call<ListCity> call, Response<ListCity> response) {
                ListCity listCity1 = response.body();
                if (listCity1 != null) {
                    for (int i = 0; i < listCity1.getLtsItem().size(); i++) {
                        listCity.add(new City(listCity1.getLtsItem().get(i).getTitle()));
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCity> call, Throwable t) {

            }
        });
        return listCity;
    }
}
