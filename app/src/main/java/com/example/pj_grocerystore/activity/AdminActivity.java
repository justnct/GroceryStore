package com.example.pj_grocerystore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ViewPagerAdapterAdmin;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapterAdmin viewPagerAdapterAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addUI();
        tableLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });
    }

    private void addUI() {
        tableLayout = findViewById(R.id.tab_admin);
        mViewPager = findViewById(R.id.vp_admin);
        viewPagerAdapterAdmin = new ViewPagerAdapterAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapterAdmin);
        tableLayout.setupWithViewPager(mViewPager);

        //tab1
        tableLayout.getTabAt(0).setIcon(R.drawable.ic_cart);
        BadgeDrawable badgeDrawableFood = tableLayout.getTabAt(0).getOrCreateBadge();
        badgeDrawableFood.setVisible(true);
        badgeDrawableFood.setNumber(getCountFood());

        //tab2
        tableLayout.getTabAt(1).setIcon(R.drawable.ic_cart);
        BadgeDrawable badgeDrawableUser = tableLayout.getTabAt(1).getOrCreateBadge();
        badgeDrawableUser.setVisible(true);
        badgeDrawableUser.setNumber(getCountUser());
    }

    private int getCountFood() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Product");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                badgeDrawableUser.setNumber(getCountUser());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return 0;
    }

    private int getCountUser() {
        return 0;
    }
}