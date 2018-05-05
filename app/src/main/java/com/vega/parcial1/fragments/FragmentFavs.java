package com.vega.parcial1.fragments;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vega.parcial1.R;
import com.vega.parcial1.adapters.ContactsRvAdapter;
import com.vega.parcial1.adapters.FavsRvAdapter;
import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavs extends Fragment {
    boolean fav;
    private View v;
    private RecyclerView recyclerView;
    private static  FragmentContacts fc;
    List<ModelContacts> list = new ArrayList<>();
    List<ModelContacts> favs = new ArrayList<>();


    public FragmentFavs(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_fav, container, false);
        recyclerView = v.findViewById(R.id.rv_fav);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        FavsRvAdapter adapter = new FavsRvAdapter(getContext(), getFavs(), true);
        recyclerView.setAdapter(adapter);

        return v;


    }

    private List<ModelContacts> getFavs(){
        for (int i = 0; i<list.size();i++){
            if (list.get(i).isFav() == true){
                prepareFavContacts(list);
            }
        }

        return favs;
    }

    public List<ModelContacts> prepareFavContacts(List<ModelContacts> contacto){
        for (int i = 0; i<contacto.size();i++){
            if(contacto.get(i).isFav()) {
                favs.add(new ModelContacts(contacto.get(i).getName(), contacto.get(i).getNumber(), contacto.get(i).isFav()));
            }
        }
        return favs;
    }

    public void update(List<ModelContacts> Contactoos) {
        Log.d("LifeCycle", "On Update");
        favs=prepareFavContacts(Contactoos);
        FavsRvAdapter adapter = new FavsRvAdapter(getContext(), getFavs(), true);
        recyclerView.setAdapter(adapter);
    }

}
