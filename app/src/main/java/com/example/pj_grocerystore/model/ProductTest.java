package com.example.pj_grocerystore.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class ProductTest implements Parcelable {
    private String name;
    private int price;
    private int type;

    public ProductTest(String name, int price, int type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
    public ProductTest(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    protected ProductTest(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
        this.type = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
        dest.writeInt(type);
    }

    public static final Creator<ProductTest> CREATOR = new Creator<ProductTest>() {
        @Override
        public ProductTest createFromParcel(Parcel source) {
            return new ProductTest(source);
        }

        @Override
        public ProductTest[] newArray(int size) {
            return new ProductTest[0];
        }
    };
}
