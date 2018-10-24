package com.example.ahmed.blank_project.databases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.ahmed.blank_project.R;
import com.example.ahmed.blank_project.parents.controllerDB;

import org.json.JSONException;
import org.json.JSONObject;


public class RemoteDB extends controllerDB {


    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public RemoteDB(Context context) {
        super(context);
    }

 /*   public String validarDevice(Device2 device, String login) {
        String retorno="";
        JSONObject aux = new JSONObject();
        String wsValidarDevice = mContext.getResources().getString(R.string.Device);
        try {

            aux.put("pim", encripta(device.getpim()));
            aux.put("imei", encripta(device.getimei()));
            aux.put("login", encripta(login));

            StringsRequest sr = new StringsRequest(mContext, wsValidarDevice, aux, "ValidarDevice");
            sr.postRequest();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return retorno;
    }*/


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }
}
