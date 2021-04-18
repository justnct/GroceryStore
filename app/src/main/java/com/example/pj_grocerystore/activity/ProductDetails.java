package com.example.pj_grocerystore.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.fragment.CartFragment;
import com.example.pj_grocerystore.fragment.StoreFragment;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.FormatNumber;
import com.example.pj_grocerystore.model.ListProductOfCustomer;
import com.example.pj_grocerystore.model.Product;

public class ProductDetails extends AppCompatActivity {
    private ImageView img_product;
    private TextView tv_nameProduct, tv_priceProduct, tv_amountProduct, tv_totalMoney;
    private Button btn_addCart;
    private EditText et_amountProduct;
    private int amount = 1;
    private Product product;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        addUI();


        //getIntent fromproductdetail
        Intent i = getIntent();
        product = (Product) i.getParcelableExtra("product");
        position = i.getIntExtra("position", 0);


        tv_nameProduct.setText(product.getName());
        tv_priceProduct.setText(FormatNumber.formatNumber(product.getPrice()));
        img_product.setImageResource(product.getImage());
        et_amountProduct.setText(String.valueOf(amount));
        tv_totalMoney.setText(FormatNumber.formatNumber(product.getPrice()));


        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(et_amountProduct.getText())) {
                    amount = Integer.valueOf(String.valueOf(et_amountProduct.getText()));
                } else {
                    amount = 1;
                }
                ListProductOfCustomer.addProduct(new DetailsProduct(product.getName(), product.getPrice(), amount, amount * product.getPrice()));
                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                i.putExtra("currentProduct", position);
                startActivity(i);
                finish();
            }
        });


    }

    private void addUI() {
        img_product = findViewById(R.id.img_imgdetailProduct);
        tv_nameProduct = findViewById(R.id.tv_nameDetailProduct);
        tv_priceProduct = findViewById(R.id.tv_priceDetailProduct);
        et_amountProduct = findViewById(R.id.tv_amount);
        tv_totalMoney = findViewById(R.id.tv_totalMoney);
        btn_addCart = findViewById(R.id.btn_addCart);

    }
}