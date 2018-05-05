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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vega.parcial1.R;
import com.vega.parcial1.adapters.ContactsRvAdapter;
import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;

public class FragmentContacts extends Fragment {

    private View v;
    private RecyclerView recyclerView;
    private static  FragmentContacts fc;
    List<ModelContacts> list = new ArrayList<>();

    ModelContacts modelo;

    public FragmentContacts(){

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_contacts, container, false);
        recyclerView = v.findViewById(R.id.rv_contacts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        ContactsRvAdapter adapter = new ContactsRvAdapter(getContext(), getContacts());
        recyclerView.setAdapter(adapter);

        return v;


    }

    public List<ModelContacts> getContacts(){



        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            //requestPermission();
        } else {

            Cursor cursor = getContext().getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");
            cursor.moveToFirst();
            while (cursor.moveToNext()) {

                modelo = new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)), false);


                list.add(new ModelContacts(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                        cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)), modelo.isFav()));

            }




            cursor.close();
        }

        return list;
    }

    public FragmentContacts getInstance(){
        return fc;
    }


}
