package com.example.dynamicmentorapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch(i){
            case 0:
                return new MentorFormFragment();
            case 1:
                return new PrefMethodsFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
