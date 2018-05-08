package com.vega.parcial1.adapters;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.parcial1.MainActivity;
import com.vega.parcial1.R;
import com.vega.parcial1.fragments.FragmentContacts;
import com.vega.parcial1.fragments.FragmentFavs;
import com.vega.parcial1.models.ModelContacts;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.content.ContextCompat.startActivity;
import static com.vega.parcial1.MainActivity.viewPager;

public class ContactsRvAdapter extends RecyclerView.Adapter<ContactsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    private static List<ModelContacts> mListContacts;
    boolean favo;
    private static FragmentFavs ff;
    public static FragmentContacts fc;
    Dialog myDialog;

    public ContactsRvAdapter(Context context, List<ModelContacts> listContacts) {
        mcontext = context;
        mListContacts = listContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_contacts, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this.favo);

        myDialog = new Dialog(mcontext);
        myDialog.setContentView(R.layout.contact_display);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        ff = FragmentFavs.getInstance();
        fc = FragmentContacts.getInstance();

        TextView contact_name, contact_number;
        ImageView boton, contact_icon;
        LinearLayout contacto;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        boton = holder.boton;
        contact_icon = holder.contact_icon;
        contacto = holder.contacto;



        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());
        contact_icon.setImageResource(R.drawable.contacto);
        boton.setImageResource(mListContacts.get(position).isFav()?R.drawable.full_star:R.drawable.star);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListContacts.get(position).setFav(true);
                if (!favo) {
                    if (!holder.fav) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.full_star);
                        holder.fav=true;

                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav=false;
                    }

                } else {
                    if (!holder.fav) {
                        mListContacts.get(position).setFav(true);
                        holder.boton.setImageResource(R.drawable.full_star);
                        holder.fav = true;

                    } else {
                        mListContacts.get(position).setFav(false);
                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = false;
                    }
                }

                viewPager.setCurrentItem(0);
                viewPager.setCurrentItem(1);

            }


        });


        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView tvname = myDialog.findViewById(R.id.display_name);
                final TextView tvnumber = myDialog.findViewById(R.id.display_number);
                tvname.setText(mListContacts.get(position).getName());
                tvnumber.setText(mListContacts.get(position).getNumber());
                myDialog.show();

                Button share = myDialog.findViewById(R.id.display_share);
                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mcontext, "Compartiendo datos", Toast.LENGTH_SHORT).show();

                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
                        shareIntent.setType("*/*");
                        shareIntent.putExtra(Intent.EXTRA_TEXT, mcontext.getString(R.string.app_nombre) + ": " + mListContacts.get(position).getName().toString() + "\n" + mcontext.getString(R.string.app_numero) + ": " + mListContacts.get(position).getNumber().toString() );
                        mcontext.startActivity(shareIntent);

                    }
                });
            }




        });



    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name, contact_number;
        ImageView boton, contact_icon;
        LinearLayout contacto;
        Button share;
        boolean fav;

        public ViewHolder(View itemView, boolean favo){
            super(itemView);

            contacto = itemView.findViewById(R.id.desplegar);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            boton = itemView.findViewById(R.id.favorito);
            contact_icon = itemView.findViewById(R.id.contact_icon);
            share = itemView.findViewById(R.id.display_share);
            fav = favo;

        }
    }
}
