package com.example.spinner4;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {


    public  pagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i ){
            case 0:
                return new fragment_one();
            case 1:
                return new fragment_two();
            default:
                break;

        }
        return null;

    }

    @Override
    public int getCount() {
        return 2;
    }
}

