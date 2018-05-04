package com.vega.parcial1.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vega.parcial1.R;
import com.vega.parcial1.models.ModelContacts;

import java.util.List;

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

        contact_name = holder.contact_name;
        contact_number = holder.contact_number;

        contact_name.setText(mListContacts.get(position).getName());
        contact_number.setText(mListContacts.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        return mListContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contact_name, contact_number;

        public ViewHolder(View itemView){
            super(itemView);

            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);

        }
    }
}
