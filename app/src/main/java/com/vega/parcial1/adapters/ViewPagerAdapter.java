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

    private List<Fragment> listFragment = new ArrayList<>();
    private List<String> listTitles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


        public List<ModelContacts> filter( String query){
        query = query.toLowerCase();
        final List<ModelContacts> filteredContactList = new ArrayList<>();
        for (ModelContacts model:list){
            final String text = model.getName().toLowerCase();
            if (text.startsWith(query)){
                filteredContactList.add(model);
                Log.d(" ViewPagerAdapter", "Agregando un modelo a la lista: " + text);
            }
        }
        return filteredContactList;
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
