package com.example.pj_grocerystore.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pj_grocerystore.fragment.CartFragment;
import com.example.pj_grocerystore.fragment.InformationFragment;
import com.example.pj_grocerystore.fragment.StoreFragment;

public class ViewPagerAdapter_Navi_View_Bottom extends FragmentStateAdapter {
    public ViewPagerAdapter_Navi_View_Bottom(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new StoreFragment();
            case 1:
                return new CartFragment();
            case 2:
                return new InformationFragment();
            default:
                return new StoreFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
