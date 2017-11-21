package com.bl4ck5pyda.bl4ck5pyda.android_test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.city;


/**
 * A simple {@link Fragment} subclass.
 */
public class City_details extends Fragment {
    private static final String CIUDAD = "clave";
    private TextView DETAILS;
    private city ciudad;

    public City_details() {
        // Required empty public constructor
    }


    public static City_details newInstance(city city) {
        City_details fragment = new City_details();
        Bundle args = new Bundle();
        args.putSerializable(CIUDAD,city);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ciudad = (city) getArguments().getSerializable(CIUDAD);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_city_details, container, false);
        DETAILS=(TextView)v.findViewById(R.id.txt_detalles);

        String nombre=ciudad.getName();
        String temp=ciudad.getTemperature();
        String hum=ciudad.getHumidity();
        String speed=ciudad.getWind_speed();
        String desc=ciudad.getWeather_desc();
        String currentDesc="";

        if(!TextUtils.isEmpty(nombre))
        {
            currentDesc+=nombre;

        }
        if(!TextUtils.isEmpty(temp))
        {
            currentDesc+="\n"+temp+" °C";

        }
        if(!TextUtils.isEmpty(hum))
        {
            currentDesc+="\n"+"Humidity:"+hum;

        }
        if(!TextUtils.isEmpty(speed))
        {
            currentDesc+="\n"+"Wind Speed:"+speed;

        }
        if(!TextUtils.isEmpty(desc))
        {
            currentDesc+="\n"+desc;

        }

        DETAILS.setText(currentDesc);


        return v;
    }

}
