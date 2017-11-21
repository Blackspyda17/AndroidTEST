package com.motivaimagine.motivaimagine_us_trial.rest_client.user;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.motivaimagine.motivaimagine_us_trial.rest_client.BaseService;
import com.motivaimagine.motivaimagine_us_trial.rest_client.BuildConfig;
import com.motivaimagine.motivaimagine_us_trial.rest_client.R;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.listeners.CitiesListener;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.Status;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.city;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gpaez on 9/21/2017.
 */

public class Controller extends BaseService {

    private static Controller INSTANCE = new Controller();
    private Controller(){}

    public static Controller getInstance(){
        return INSTANCE;
    }



    public void country_list(Context context,String lat,String lon,  final CitiesListener listener) {
        final ArrayList<city> cities = new ArrayList<>();
        if (listener == null)
            return;
        listener.onStart();
        String url = BuildConfig.REST_URL.concat(String.format(context.getString(R.string.uri_cities), lat, lon));

        JsonObjectRequest request = getDefaultRequest(Request.Method.GET, url, null, false, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray ja_data = response.getJSONArray("list");
                    int length = ja_data.length();
                    for(int i=0; i<length; i++)
                    {
                        JSONObject jObj = ja_data.getJSONObject(i);
                        String name=jObj.optString("name");
                        JSONObject jhumidity=jObj.getJSONObject("main");
                        String temp=jhumidity.optString("temp");
                        String humidity=jhumidity.optString("humidity");
                        JSONObject jwind=jObj.getJSONObject("wind");
                        String speed=jwind.optString("speed");
                        JSONArray jawheater=jObj.getJSONArray("weather");
                        JSONObject jawheater1=jawheater.getJSONObject(0);
                        String wheater_desc=jawheater1.optString("description");
                        cities.add(new city(name,wheater_desc,temp,speed,humidity));
                    }


                    if (cities.isEmpty()) {
                        listener.onFailed(new Status(false, "Couldn't Complete the request, any city available near"));
                    }else{

                        listener.onCompleted(cities);
                    }


                } catch (Exception e) {

                    Status status = new Status(false, "Server Error");
                    listener.onFailed(status);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                Status status = new Status(false, "Transaction Error");
                listener.onFailed(status);

            }
        });


        getDefaultQueue(context).add(request);

    }


}
