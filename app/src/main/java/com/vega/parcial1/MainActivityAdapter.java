package com.vega.parcial1;

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

import com.vega.parcial1.models.ModelContacts;

import java.util.ArrayList;
import java.util.List;

public class MainActivityAdapter  extends RecyclerView.Adapter<MainActivityAdapter.HolderView>{

    private LayoutInflater inflater;
    private Context mcontext;
    private List<ModelContacts> modelContactsList;

    public MainActivityAdapter(Context mcontext, List<ModelContacts> modelContactsList) {
        this.mcontext = mcontext;
        this.modelContactsList = modelContactsList;
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);

        return new HolderView(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, final int position) {

        holder.contact_name.setText(modelContactsList.get(position).getName());
        holder.contact_number.setText(modelContactsList.get(position).getNumber());
        holder.contact_icon.setImageResource(R.drawable.contacto);


    }

    @Override
    public int getItemCount() {
        return modelContactsList.size();
    }

    public void setFilter(List<ModelContacts> contactos){

        modelContactsList = new ArrayList<>();
        modelContactsList.addAll(contactos);
        notifyDataSetChanged();

    }

    public class HolderView extends RecyclerView.ViewHolder{

        ImageView contact_icon;
        TextView contact_name, contact_number;
        Button llamar;

        public HolderView(View itemView) {
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            contact_icon = itemView.findViewById(R.id.contact_icon);
        }
    }
}
