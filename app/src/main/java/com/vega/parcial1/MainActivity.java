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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.parcial1.adapters.ViewPagerAdapter;
import com.vega.parcial1.fragments.FragmentCalls;
import com.vega.parcial1.fragments.FragmentContacts;
import com.vega.parcial1.fragments.FragmentFavs;
import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

import static com.vega.parcial1.fragments.FragmentContacts.list;

public class MainActivity extends AppCompatActivity {


    private static TabLayout tabLayout;
    public static ViewPager viewPager;
    private int code = 14;
    public static  int c =1;

    private static final int[] ICONS = {(R.drawable.llamada), (R.drawable.contacto), (R.drawable.star)};

    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    public final FragmentContacts fc = new FragmentContacts();

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
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_bar, menu);
        MenuItem item = menu.findItem(R.id.searchbar);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {

                FragmentContacts.auxlist();
                updatepager(1);
                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean newViewFocus)
            {
                if (!newViewFocus)
                {
                    FragmentContacts.auxlist();
                    updatepager(1);

                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fc.filter(newText);
                viewPager.setCurrentItem(1);
                FragmentContacts.updatefc();
                updatepager(1);

                return false;
            }


        });

        return true;
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

    public static void updatepager(int d){

        viewPager.setAdapter(viewPager.getAdapter());
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }
        viewPager.setCurrentItem(d);
    }




}



