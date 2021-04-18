package com.example.pj_grocerystore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pj_grocerystore.R;
import com.example.pj_grocerystore.adapter.ViewPagerAdapter_Tutorial;

import me.relex.circleindicator.CircleIndicator;

public class IntroduceAppActivity extends AppCompatActivity {
    private TextView tv_skip, tv_next;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private ViewPagerAdapter_Tutorial viewPagerAdapter_tutorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_app);
        //declare
        tv_next = (TextView) findViewById(R.id.next_introduce_game);
        tv_skip = (TextView) findViewById(R.id.skip_introduce_game);
        viewPager = (ViewPager) findViewById(R.id.viewPager_introduceGame);
        circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
        viewPagerAdapter_tutorial = new ViewPagerAdapter_Tutorial(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter_tutorial);
        circleIndicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 1){
                    tv_skip.setVisibility(View.GONE);
                    tv_next.setVisibility(View.GONE);
                }else {
                    tv_skip.setVisibility(View.VISIBLE);
                    tv_next.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem() < 1){
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
    }
    }
