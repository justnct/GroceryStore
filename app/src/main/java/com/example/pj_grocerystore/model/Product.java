package com.example.pj_grocerystore.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Product implements Parcelable {
    private String name;
    private int price;
    private int image;
    private int type;
    public static int TYPE_VEGETABLES = 1;
    public static int TYPE_MEAT = 2;
    public static int TYPE_FLOWER = 3;



    public Product(String name, int price, int image, int type) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    protected Product(Parcel in) {
        this.name = in.readString();
        this.price = in.readInt();
        this.image = in.readInt();
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
        dest.writeInt(image);
        dest.writeInt(type);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[0];
        }
    };
}
