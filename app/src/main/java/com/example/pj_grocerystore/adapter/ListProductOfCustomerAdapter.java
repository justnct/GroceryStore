package com.example.pj_grocerystore.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.fragment.CartFragment;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.FormatNumber;
import com.example.pj_grocerystore.model.ListProductOfCustomer;
import com.example.pj_grocerystore.model.Product;

import java.util.List;

public class ListProductOfCustomerAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<DetailsProduct> productList;

    public ListProductOfCustomerAdapter(Context context, int layout, List<DetailsProduct> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView tv_name;
        TextView tv_price;
        TextView tv_amount;
        TextView tv_total;
        ImageView img_deleteProduct;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = convertView.findViewById(R.id.name_product_customer);
            viewHolder.tv_price = convertView.findViewById(R.id.price_product_customer);
            viewHolder.tv_amount = convertView.findViewById(R.id.amount_product_customer);
            viewHolder.tv_total = convertView.findViewById(R.id.total_product_customer);
            viewHolder.img_deleteProduct = convertView.findViewById(R.id.detele_item_cart);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(productList.get(position).getName());
        viewHolder.tv_price.setText(FormatNumber.formatNumber(productList.get(position).getPrice()));
        viewHolder.tv_amount.setText(String.valueOf(productList.get(position).getAmount()));
        viewHolder.tv_total.setText(String.valueOf(productList.get(position).getTotal()));
        viewHolder.img_deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b = new AlertDialog.Builder(context);
                b.setTitle("Xác nhận");
                b.setMessage("Bạn đồng ý xoá sản phẩm này không ?");
                b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ListProductOfCustomer.removeProduct(productList.get(position));
                        notifyDataSetChanged();
                    }
                });
                b.setNegativeButton("Không đồng ý", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog al = b.create();
                al.setCancelable(false);
                al.show();
            }
        });

        return convertView;

    }
}
