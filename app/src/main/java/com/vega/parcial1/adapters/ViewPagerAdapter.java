package com.vega.parcial1.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

import static com.vega.parcial1.fragments.FragmentContacts.list;

/**
 * Created by Ronald Vega 00038015 on 5/3/2018
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private static List<Fragment> listFragment = new ArrayList<>();
    private List<String> listTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitles.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        listFragment.add(fragment);
        listTitles.add(title);
    }



}
