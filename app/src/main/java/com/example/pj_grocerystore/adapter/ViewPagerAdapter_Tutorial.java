package com.example.pj_grocerystore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class ViewPagerAdapter_Tutorial extends FragmentStatePagerAdapter {
    public ViewPagerAdapter_Tutorial(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tutorial_1();
            case 1:
                return new Tutorial_2();
            default:
                return new Tutorial_1();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
