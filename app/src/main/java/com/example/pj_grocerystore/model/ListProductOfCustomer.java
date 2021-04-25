package com.example.pj_grocerystore.model;

import android.content.Context;

import com.example.pj_grocerystore.adapter.ListProductOfCustomerAdapter;
import com.example.pj_grocerystore.fragment.CartFragment;

import java.util.ArrayList;
import java.util.List;

public class ListProductOfCustomer {
    private static ArrayList<DetailsProduct> detailsProductList = new ArrayList<>();

    public static ArrayList<DetailsProduct> getDetailsProductList() {
        return detailsProductList;
    }

    public static void addProduct(DetailsProduct product) {
        //cart empty
        if (detailsProductList.isEmpty()) {
            detailsProductList.add(product);
        } else {
            //cart have item
            boolean pass = false;
            for (int i = 0; i < detailsProductList.size(); i++) {
                //buy more
                if (detailsProductList.get(i).getName().equals(product.getName())) {
                    DetailsProduct newProduct = null;
                    DetailsProduct currentProduct = detailsProductList.get(i);
                    currentProduct.setAmount(currentProduct.getAmount() + product.getAmount());
                    currentProduct.setTotal(currentProduct.getAmount() * currentProduct.getPrice());
                    newProduct = currentProduct;
                    detailsProductList.add(newProduct);
                    detailsProductList.remove(currentProduct);
                    pass = true;
                }
            }
            if (!pass) {
                detailsProductList.add(product);
            }
        }
    }

    public static void cleanProduct() {
        detailsProductList.clear();
    }

    public static void removeProduct(DetailsProduct product) {
        detailsProductList.remove(product);
    }
}
