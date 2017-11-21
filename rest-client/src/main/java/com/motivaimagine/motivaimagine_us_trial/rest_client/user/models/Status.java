package com.motivaimagine.motivaimagine_us_trial.rest_client.user.models;

import java.io.Serializable;

/**
 * Created by gpaez on 10/11/2017.
 */

public class Status implements Serializable {

    private boolean status;
    private String code;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Status(boolean status, String code) {
        this.status = status;
        this.code = code;
    }

}
