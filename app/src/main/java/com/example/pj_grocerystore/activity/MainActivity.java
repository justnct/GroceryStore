package com.example.pj_grocerystore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ViewPagerAdapter_Navi_View_Bottom;
import com.example.pj_grocerystore.animation_of_viewpager2.ZoomOutPageTransformer;
import com.example.pj_grocerystore.model.Account;
import com.example.pj_grocerystore.shared_preference.DataLocalManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;
    private ViewPagerAdapter_Navi_View_Bottom viewPagerAdapter_navi_view_bottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declare
        addUI();
        //action viewpager
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.navi_view_store).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.navi_view_something_i_just_want_sell).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.navi_view_cart).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.navi_view_information).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        //action bottom_navi_view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navi_view_store:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.navi_view_something_i_just_want_sell:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.navi_view_cart:
                        viewPager2.setCurrentItem(2);
                        break;
                    case R.id.navi_view_information:
                        viewPager2.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }


    private void addUI() {
        viewPager2 = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNaviView);
        viewPagerAdapter_navi_view_bottom = new ViewPagerAdapter_Navi_View_Bottom(this);
        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setAdapter(viewPagerAdapter_navi_view_bottom);
    }
    
}