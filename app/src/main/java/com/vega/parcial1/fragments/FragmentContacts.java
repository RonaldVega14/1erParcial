package com.vega.parcial1.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vega.parcial1.R;
import com.vega.parcial1.adapters.ContactsRvAdapter;
import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

import static com.vega.parcial1.MainActivity.c;

public class FragmentContacts extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private static  FragmentContacts fc;
    public static List<ModelContacts> list = new ArrayList<>();

    public static List<ModelContacts> filteredContactList = new ArrayList<>();
    public static List<ModelContacts> aux = new ArrayList<>();
    ContactsRvAdapter adapter;

    ModelContacts modelo;


    public FragmentContacts(){

    }

    public static FragmentContacts newInstance() {

        FragmentContacts fragment = new FragmentContacts();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_contacts, container, false);
        recyclerView = v.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ContactsRvAdapter(getContext(), getContacts());
        recyclerView.setAdapter(adapter);

        return v;


    }

    public List<ModelContacts> getContacts(){
        if(list.size() == 0) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                //requestPermission();
            } else {


                Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
                cursor.moveToFirst();
                while (cursor.moveToNext()) {

                    list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)), false));

                }

                cursor.close();
            }
        }
        else{

        }

        return list;
    }

    public static FragmentContacts getInstance(){
        return fc;
    }

    public List<ModelContacts> filter( String query){
        query = query.toLowerCase();

        if (c==1){
            aux=list;
        }
        c=0;
        filteredContactList= new ArrayList<>();
        for ( ModelContacts model:aux){
            final String text = model.getName().toLowerCase();
            if (text.startsWith(query)){
                filteredContactList.add(model);
            }
        }
        return filteredContactList;
    }

    public static void updatefc(){

        list = filteredContactList;

    }

    public static void auxlist(){
        list=aux;
        c=1;
    }


}
