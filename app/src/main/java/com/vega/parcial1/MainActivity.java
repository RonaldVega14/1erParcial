package com.vega.parcial1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.parcial1.adapters.ViewPagerAdapter;
import com.vega.parcial1.fragments.FragmentCalls;
import com.vega.parcial1.fragments.FragmentContacts;
import com.vega.parcial1.fragments.FragmentFavs;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int code = 14;

    private final int[] ICONS = {(R.drawable.llamada), (R.drawable.contacto), (R.drawable.star)};

    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {

        if (requestCode == code
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            viewPager.setAdapter(adapter);
            for(int i=0; i<tabLayout.getTabCount(); i++){
                TabLayout.Tab tab= tabLayout.getTabAt(i);
                tab.setIcon(ICONS[i]);
            }



        }

        if(grantResults[0]==PackageManager.PERMISSION_DENIED || grantResults[1]==PackageManager.PERMISSION_DENIED || grantResults[2] == PackageManager.PERMISSION_DENIED){


            requestPermission();
            Toast.makeText(MainActivity.this, "SE NECESITAN LOS PERMISOS", Toast.LENGTH_SHORT).show();
        }
    }

    public void requestPermission(){
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE},code);

    }

}



