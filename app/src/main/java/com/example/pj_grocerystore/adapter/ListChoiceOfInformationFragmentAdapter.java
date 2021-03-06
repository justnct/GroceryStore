package com.example.pj_grocerystore.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.activity.IntroduceAppActivity;
import com.example.pj_grocerystore.activity.LogInActivity;
import com.example.pj_grocerystore.activity.ShowHistoryTrans;
import com.example.pj_grocerystore.fragment.CartFragment;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.model.ChoiceOfInformation;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListChoiceOfInformationFragmentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ChoiceOfInformation> listChoice;
    private int layout;

    public ListChoiceOfInformationFragmentAdapter(Context context, ArrayList<ChoiceOfInformation> listChoice, int layout) {
        this.context = context;
        this.listChoice = listChoice;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return listChoice.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        LinearLayout main;
        ImageView img;
        TextView title1;
        TextView title2;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layout, null);
            viewHolder = new viewHolder();
            viewHolder.main = convertView.findViewById(R.id.main_item);
            viewHolder.img = convertView.findViewById(R.id.img_pic);
            viewHolder.title1 = convertView.findViewById(R.id.title1);
            viewHolder.title2 = convertView.findViewById(R.id.title2);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (viewHolder) convertView.getTag();
        }
        viewHolder.img.setImageResource(listChoice.get(position).getImage());
        viewHolder.title1.setText(listChoice.get(position).getTitle1());
        viewHolder.title2.setText(listChoice.get(position).getTitle2());
        viewHolder.main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (listChoice.get(position).getTitle1()){
                    case "LogOut":
                        String username = DataLocalManager.getAccount("Account").getUsername();
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Account");
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    Account test = dataSnapshot.getValue(Account.class);
                                    if(test.getUsername().equals(username)){
                                        test.setIsActive(0);
                                        databaseReference.child(username).setValue(test);
                                        DataLocalManager.removeKey("Account");
                                        Intent i = new Intent(context, LogInActivity.class);
                                        context.startActivity(i);
                                        ((Activity)context).finish();
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        break;

                    case "Introduce":
                        Intent i = new Intent(context, IntroduceAppActivity.class);
                        context.startActivity(i);
                        break;
                    case "History":
                        Intent intend = new Intent(context, ShowHistoryTrans.class);
                        context.startActivity(intend);
                        break;
                }
            }
        });
        return convertView;
    }
}
