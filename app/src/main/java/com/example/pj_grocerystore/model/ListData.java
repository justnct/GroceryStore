package com.example.pj_grocerystore.model;

import com.example.pj_grocerystore.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ListData {
    public static ArrayList<Product> getListData(int type) {
        ArrayList<Product> listAllProduct = new ArrayList<>();
        ArrayList<Product> listProduct = new ArrayList<>();

        
        //add product
        listAllProduct.add(new Product("Mồng tơi", 5000, R.drawable.mongtoi, Product.TYPE_VEGETABLES));
        listAllProduct.add(new Product("Rau lang ", 5500, R.drawable.raulang, Product.TYPE_VEGETABLES));
        listAllProduct.add(new Product("Rau muống", 4000, R.drawable.raumuong, Product.TYPE_VEGETABLES));
        listAllProduct.add(new Product("Rau dền", 4500, R.drawable.rauden, Product.TYPE_VEGETABLES));
        listAllProduct.add(new Product("Sườn non", 45000, R.drawable.suonnon, Product.TYPE_MEAT));
        listAllProduct.add(new Product("Thịt bò", 55000, R.drawable.thitbo, Product.TYPE_MEAT));
        listAllProduct.add(new Product("Thịt gà", 65000, R.drawable.thitga, Product.TYPE_MEAT));
        listAllProduct.add(new Product("Hoa hồng", 180000, R.drawable.hoahong, Product.TYPE_FLOWER));
        listAllProduct.add(new Product("Hoa hướng dương", 170000, R.drawable.huongduong, Product.TYPE_FLOWER));
        listAllProduct.add(new Product("Phóng lợn", 20000, R.drawable.phonglon, Product.TYPE_MEAT));


        switch (type) {
            case 0:
                listProduct.addAll(listAllProduct);
                break;
            case 1:
                for (int i = 0; i < listAllProduct.size(); i++) {
                    if (listAllProduct.get(i).getType() == Product.TYPE_VEGETABLES) {
                        listProduct.add(listAllProduct.get(i));
                    }
                }
                break;
            case 2:
                for (int i = 0; i < listAllProduct.size(); i++) {
                    if (listAllProduct.get(i).getType() == Product.TYPE_MEAT) {
                        listProduct.add(listAllProduct.get(i));
                    }
                }
                break;
            case 3:
                for (int i = 0; i < listAllProduct.size(); i++) {
                    if (listAllProduct.get(i).getType() == Product.TYPE_FLOWER) {
                        listProduct.add(listAllProduct.get(i));
                    }
                }
                break;
        }
        return listProduct;
    }
}
