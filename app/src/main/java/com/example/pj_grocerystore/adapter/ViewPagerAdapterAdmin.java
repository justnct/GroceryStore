package com.example.pj_grocerystore.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pj_grocerystore.fragment.FoodManagementFragment;
import com.example.pj_grocerystore.fragment.UserManagementFragment;

public class ViewPagerAdapterAdmin extends FragmentStatePagerAdapter {
    public ViewPagerAdapterAdmin(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FoodManagementFragment();
            case 1:
                return new UserManagementFragment();
            default:
                return new FoodManagementFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "FOOD";
                break;
            case 1:
                title = "USER";
                break;
        }
        return title;
    }
}
