package com.example.pj_grocerystore.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.ProductDetails;
import com.example.pj_grocerystore.model.FormatNumber;
import com.example.pj_grocerystore.model.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductviewHolder> {
    private ArrayList<Product> mListProduct;

    public ProductAdapter(ArrayList<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    @NonNull
    @Override
    public ProductviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_product, parent, false);
        return new ProductviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductviewHolder holder, int position) {
        Product product = mListProduct.get(position);
        holder.img_product.setImageResource(product.getImage());
        holder.tv_nameProduct.setText(product.getName());
        holder.tv_priceProduct.setText(FormatNumber.formatNumber(product.getPrice()));
        holder.main_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetails.class);
                intent.putExtra("product", product);
                intent.putExtra("position", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public void filterList(ArrayList<Product> listSearch) {
        mListProduct = listSearch;
        notifyDataSetChanged();
    }

    public class ProductviewHolder extends RecyclerView.ViewHolder {
        private ImageView img_product;
        private TextView tv_nameProduct, tv_priceProduct;
        private LinearLayout main_details;

        public ProductviewHolder(@NonNull View itemView) {
            super(itemView);
            main_details = itemView.findViewById(R.id.main_details);
            img_product = itemView.findViewById(R.id.img_product);
            tv_nameProduct = itemView.findViewById(R.id.tv_nameProduct);
            tv_priceProduct = itemView.findViewById(R.id.tv_priceProduct);
        }
    }
}
