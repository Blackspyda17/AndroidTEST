package com.motivaimagine.motivaimagine_us_trial.rest_client.user.listeners;

import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.Status;
import com.motivaimagine.motivaimagine_us_trial.rest_client.user.models.city;

import java.util.ArrayList;

/**
 * Created by gpaez on 11/19/2017.
 */

public interface CitiesListener {

    void onStart();
    void onCompleted( ArrayList<city> cities);
    void onFailed(Status Error);


}
