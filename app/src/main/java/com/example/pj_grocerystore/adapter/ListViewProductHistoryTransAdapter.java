package com.example.pj_grocerystore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.model.DetailsProduct;
import com.example.pj_grocerystore.model.FormatNumber;

import java.util.ArrayList;

public class ListViewProductHistoryTransAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<DetailsProduct> list;

    public ListViewProductHistoryTransAdapter(Context context, int layout, ArrayList<DetailsProduct> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView tv_name, tv_price, tv_amount, tv_total;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = convertView.findViewById(R.id.name_product_history);
            viewHolder.tv_price = convertView.findViewById(R.id.price_product_history);
            viewHolder.tv_amount = convertView.findViewById(R.id.amount_product_history);
            viewHolder.tv_total = convertView.findViewById(R.id.total_product_history);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getName());
        viewHolder.tv_price.setText(FormatNumber.formatNumber(list.get(position).getPrice()));
        viewHolder.tv_amount.setText(String.valueOf(list.get(position).getAmount()));
        viewHolder.tv_total.setText(FormatNumber.formatNumber(list.get(position).getTotal()));
        return convertView;
    }
}
