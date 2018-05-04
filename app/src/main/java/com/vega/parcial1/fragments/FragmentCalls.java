package com.vega.parcial1.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;

import com.vega.parcial1.R;
import com.vega.parcial1.adapters.CallsRvAdapter;
import com.vega.parcial1.models.ModelCalls;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentCalls extends Fragment {

    private RecyclerView recyclerView;
    private View v;
    private int request = 14;

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

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            requestPermission();
            }


        else{

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int duration_idx = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int date_idx = cursor.getColumnIndex(CallLog.Calls.DATE);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);

        cursor.moveToFirst();
        cursor.moveToPrevious();
        while (cursor.moveToNext()) {

            String callType = cursor.getString(type);
            String dir = null;

            Date date = new Date(Long.valueOf(cursor.getString(date_idx)));

            DateFormat longDF = DateFormat.getDateInstance(DateFormat.LONG);
            DateFormat shortDfH = DateFormat.getTimeInstance(DateFormat.SHORT);

            int callTypeCode = Integer.parseInt(callType);
            switch (callTypeCode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "Outgoing";
                    break;

                case CallLog.Calls.INCOMING_TYPE:
                    dir = "Incoming";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "Missed";
                    break;
            }

            list.add(new ModelCalls(cursor.getString(number), CalcularTiempo(cursor.getString(duration_idx)), longDF.format(date) + " " + shortDfH.format(date)));

        }

        cursor.close();

        }

        return list;

    }


    private String CalcularTiempo(String segundos)
    {
        String txtH, txtM, txtS;
        int tsegundos = Integer.parseInt(segundos);

        int horas = (tsegundos / 3600);
        int minutos = ((tsegundos-horas*3600)/60);
        int segundo = tsegundos-(horas*3600)-(minutos*60);

        String duracion;

        if(horas <= 9){
            txtH = "0" + Integer.toString(horas);
        } else{ txtH = Integer.toString(horas); }

        if(minutos <= 9){
            txtM = "0" + Integer.toString(minutos);
        } else{ txtM = Integer.toString(minutos); }

        if(segundo <= 9){
            txtS = "0" + Integer.toString(segundo);
        } else{ txtS = Integer.toString(segundo); }


        duracion = txtH + ":" + txtM + ":" + txtS;
        return duracion;
    }

    public void requestPermission(){
        requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG},request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == request
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            CallsRvAdapter adapter = new CallsRvAdapter(getContext(), getCallLogs());


            recyclerView.setAdapter(adapter);
        }
        else{
            requestPermission();
        }

    }
}
