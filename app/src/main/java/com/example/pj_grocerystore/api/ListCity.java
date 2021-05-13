package com.example.pj_grocerystore.api;

import java.util.ArrayList;

public class ListCity {
    private ArrayList<City> LtsItem;
    private int TotalDoanhNghiep;

    public ArrayList<City> getLtsItem() {
        return LtsItem;
    }

    public void setLtsItem(ArrayList<City> ltsItem) {
        LtsItem = ltsItem;
    }

    public int getTotalDoanhNghiep() {
        return TotalDoanhNghiep;
    }

    public void setTotalDoanhNghiep(int totalDoanhNghiep) {
        TotalDoanhNghiep = totalDoanhNghiep;
    }
}
