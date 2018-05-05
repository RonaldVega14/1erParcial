package com.vega.parcial1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class FavsRvAdapter extends RecyclerView.Adapter<FavsRvAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private Context mcontext;
    private boolean fav;
    private List<ModelContacts> mListFavs;

    public FavsRvAdapter(Context mcontext, List<ModelContacts> mListFavs, Boolean favo) {
        this.mcontext = mcontext;
        this.mListFavs = mListFavs;
        this.fav = favo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.item_favs, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, this.fav);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        TextView contact_name, contact_number;

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;
        contact_name.setText(mListFavs.get(position).getName());
        contact_number.setText(mListFavs.get(position).getNumber());

    }

    @Override
    public int getItemCount() {
        return mListFavs.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        Button butt;
        TextView contact_name, contact_number;

        public ViewHolder(View itemView, boolean favs){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
        }
    }
}
