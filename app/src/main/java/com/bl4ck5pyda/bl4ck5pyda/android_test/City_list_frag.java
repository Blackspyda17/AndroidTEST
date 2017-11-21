package com.bl4ck5pyda.bl4ck5pyda.android_test;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.motivaimagine.motivaimagine_us_trial.rest_client.user.Controller;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.listeners.CitiesListener;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.Status;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.city;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class City_list_frag extends Fragment {
    private static final String LONG = "longitud";
    private static final String LAT = "latitud";
    public View rootView;
    private double longi;
    private double lati;
    public RecyclerView rv;
    public city_adapter adapter;
    LinearLayoutManager llm;
    public ArrayList<city> LCities=new ArrayList<>();
    public ProgressDialog progressDialog ;

    public City_list_frag() {
        // Required empty public constructor
    }

    public static City_list_frag newInstance( double longi, double lati) {
        City_list_frag fragment = new City_list_frag();
        Bundle args = new Bundle();
        args.putDouble(LONG, longi);
        args.putDouble(LAT, lati);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            longi = getArguments().getDouble(LONG);
            lati = getArguments().getDouble(LAT);

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_city_list_frag, container, false);

        progressDialog=new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading the doctors list, please wait...");


        cargar_ciudades();
        rv = (RecyclerView)rootView.findViewById(R.id.rv_cities);
        rv.setHasFixedSize(true);


        adapter = new city_adapter(getContext(),LCities);
        rv.setAdapter(adapter);


        llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        return rootView;
    }

    private void cargar_ciudades() {
        Controller.getInstance().country_list(getContext(),lati+"",longi+"",new City_list_frag.CityCallback());

    }



    public  class CityCallback implements CitiesListener {

        @Override
        public void onStart() {
            progressDialog.show();
        }

        @Override
        public void onCompleted(ArrayList<city> cities) {
            progressDialog.dismiss();
            LCities=cities;

            rv = (RecyclerView)rootView.findViewById(R.id.rv_cities);
            rv.setHasFixedSize(true);


            adapter = new city_adapter(getContext(),LCities);
            rv.setAdapter(adapter);


            llm = new LinearLayoutManager(getContext());
            rv.setLayoutManager(llm);
        }

        @Override
        public void onFailed(Status Error) {
            String mensaje = Error.getCode();
            if (Error.getCode().equals("301")){
                mensaje= "Error Parsing Data";
            }
            progressDialog.dismiss();

            Toast.makeText(getContext(),mensaje, Toast.LENGTH_SHORT).show();


        }
    }


}
