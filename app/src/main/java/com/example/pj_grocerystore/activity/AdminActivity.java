package com.example.pj_grocerystore.activity;

import android.os.Bundle;
import android.widget.TableLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ViewPagerAdapterAdmin;
import com.google.android.material.tabs.TabLayout;

public class AdminActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapterAdmin viewPagerAdapterAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        addUI();
    }

    private void addUI() {
        tableLayout = findViewById(R.id.tab_admin);
        mViewPager = findViewById(R.id.vp_admin);
        viewPagerAdapterAdmin = new ViewPagerAdapterAdmin(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapterAdmin);
        tableLayout.setupWithViewPager(mViewPager);

    }
}