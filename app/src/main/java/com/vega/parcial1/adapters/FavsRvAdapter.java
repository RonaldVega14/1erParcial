package com.vega.parcial1.adapters;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vega.parcial1.R;
import com.vega.parcial1.fragments.FragmentContacts;
import com.vega.parcial1.fragments.FragmentFavs;
import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

import static com.vega.parcial1.MainActivity.updatepager;
import static com.vega.parcial1.MainActivity.viewPager;
import static com.vega.parcial1.fragments.FragmentContacts.list;

public class FavsRvAdapter extends RecyclerView.Adapter<FavsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    private boolean favo;
    public List<ModelContacts> mListFavs;

    public FavsRvAdapter(Context mcontext, List<ModelContacts> mListFavs) {
        this.mcontext = mcontext;
        this.mListFavs = mListFavs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_favs, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TextView contact_name, contact_number;
        ImageView boton;
        Button llamando;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        boton = holder.boton;
        llamando = holder.llamando;

        contact_name.setText(mListFavs.get(position).getName());
        contact_number.setText(mListFavs.get(position).getNumber());
        boton.setImageResource(mListFavs.get(position).isFav()?R.drawable.full_star:R.drawable.star);

        llamando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llamar(mListFavs.get(position).getNumber());
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!favo) {
                    if (!holder.fav) {

                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName()) && mListFavs.get(position).getNumber().equals(list.get(i).getNumber() )) {
                                list.get(i).setFav(false);
                            }
                        }




                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav=false;


                    } else {
                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName()) && mListFavs.get(position).getNumber().equals(list.get(i).getNumber() )) {
                                list.get(i).setFav(true);
                            }
                        }
                        holder.boton.setImageResource(R.drawable.full_star);
                        holder.fav=true;


                    }

                } else {
                    if (holder.fav) {
                        mListFavs.get(position).setFav(false);
                        for (int i = 0; i<list.size();i++){

                            if(mListFavs.get(position).getName().equals(list.get(i).getName())) {
                                list.get(i).setFav(false);
                            }
                        }
                        holder.boton.setImageResource(R.drawable.full_star);
                        holder.fav = true;

                    } else {
                        mListFavs.get(position).setFav(false);

                        holder.boton.setImageResource(R.drawable.star);
                        holder.fav = false;
                    }

                }
                updatepager(2);

            }
        });

    }

    @Override
    public int getItemCount() {
        return mListFavs.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView contact_name, contact_number;
        ImageView boton;
        Button llamando;
        boolean fav;

        public ViewHolder(View itemView){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            boton = itemView.findViewById(R.id.favorito);
            llamando = itemView.findViewById(R.id.llamar);
        }
    }

    private void llamar(final String phoneNumber) {
        if (ActivityCompat.checkSelfPermission(mcontext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//              requestPermissions(mcontext,Manifest.permission.CALL_PHONE,1);
        }else{
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+phoneNumber));
            mcontext.startActivity(intent);
        }

    }
}
