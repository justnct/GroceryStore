package com.example.pj_grocerystore.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.ProductDetails;
import com.example.pj_grocerystore.model.FormatNumber;
import com.example.pj_grocerystore.model.ProductTest;

import java.util.ArrayList;

public class LikeIsSellProductAdapter extends RecyclerView.Adapter<LikeIsSellProductAdapter.LikeIsSellViewHolder> {
    private ArrayList<ProductTest> mListProduct;
    private Context context;

    public LikeIsSellProductAdapter(ArrayList<ProductTest> mListProduct, Context context) {
        this.mListProduct = mListProduct;
        this.context = context;
    }

    @NonNull
    @Override
    public LikeIsSellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_product, parent, false);
        return new LikeIsSellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeIsSellViewHolder holder, int position) {
        ProductTest productTest = mListProduct.get(position);
        holder.tv_nameProduct.setText(productTest.getName());
        Glide.with(context).load(mListProduct.get(position).getImgURL()).into(holder.img_product);
        holder.tv_priceProduct.setText(FormatNumber.formatNumber(productTest.getPrice()));
        holder.main_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductDetails.class);
                intent.putExtra("productLike", productTest);
                intent.putExtra("positionLike", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public static class LikeIsSellViewHolder extends RecyclerView.ViewHolder{
        private final ImageView img_product;
        private final TextView tv_nameProduct;
        private final TextView tv_priceProduct;
        private final LinearLayout main_details;

        public LikeIsSellViewHolder(@NonNull View itemView) {
            super(itemView);
            main_details = itemView.findViewById(R.id.main_details);
            img_product = itemView.findViewById(R.id.img_product);
            tv_nameProduct = itemView.findViewById(R.id.tv_nameProduct);
            tv_priceProduct = itemView.findViewById(R.id.tv_priceProduct);
        }
    }
}
