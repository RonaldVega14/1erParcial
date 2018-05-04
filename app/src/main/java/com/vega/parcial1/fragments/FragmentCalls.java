package com.vega.parcial1.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vega.parcial1.R;
import com.vega.parcial1.adapters.CallsRvAdapter;
import com.vega.parcial1.models.ModelCalls;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentCalls extends Fragment {

    private RecyclerView recyclerView;
    private View v;

    public FragmentCalls(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.frag_calls, container, false);
        recyclerView = v.findViewById(R.id.rv_calls);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLogs());

        recyclerView.setAdapter(adapter);

        return v;


    }

    private List<ModelCalls> getCallLogs(){

        List<ModelCalls> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        }

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " ASC");

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date_idx = cursor.getColumnIndex(CallLog.Calls.DATE);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {

            Date date = new Date(Long.valueOf(cursor.getString(date_idx)));

            DateFormat longDF = DateFormat.getDateInstance(DateFormat.LONG);
            DateFormat shortDfH = DateFormat.getTimeInstance(DateFormat.SHORT);

            list.add(new ModelCalls(cursor.getString(number), cursor.getString(duration), longDF.format(date) + " " + shortDfH.format(date)));

            Log.d("MiC::", cursor.getString(number));
        }



        return list;
    }
}
