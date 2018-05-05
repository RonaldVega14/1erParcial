package com.vega.parcial1.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.parcial1.MainActivity;
import com.vega.parcial1.R;
import com.vega.parcial1.models.ModelContacts;

import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.ContextCompat.startActivity;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    private List<ModelContacts> mListContacts;

    public ContactsRvAdapter(Context context, List<ModelContacts> listContacts) {
        mcontext = context;
        mListContacts = listContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_contacts, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView contact_name, contact_number;
        //Button boton;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
       // boton = holder.boton;


        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
        /*boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext, "Realizando llamada...", Toast.LENGTH_LONG).show();


            }
        });*/


    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name, contact_number;
        Button boton;

        public ViewHolder(View itemView){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            boton = itemView.findViewById(R.id.llamar);

        }
    }
}
