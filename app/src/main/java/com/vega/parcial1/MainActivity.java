package com.vega.parcial1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vega.parcial1.adapters.ViewPagerAdapter;
import com.vega.parcial1.fragments.FragmentCalls;
import com.vega.parcial1.fragments.FragmentContacts;
import com.vega.parcial1.fragments.FragmentFavs;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private final int[] ICONS = {(R.drawable.llamada), (R.drawable.contacto), (R.drawable.star)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentCalls(), getString(R.string.tab_lamada));
        adapter.addFragment(new FragmentContacts(), getString(R.string.tab_contacto));
        adapter.addFragment(new FragmentFavs(), getString(R.string.tab_fav));

        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);



        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }


    }

    public void setPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }

    }

}



