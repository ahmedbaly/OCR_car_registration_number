package com.example.ahmed.blank_project.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmed.blank_project.interfaces.ListenerRequest;
import com.example.ahmed.blank_project.parents.BaseActivity;
import com.example.ahmed.blank_project.parents.BaseRequest;

import org.json.JSONArray;
import org.json.JSONObject;


public class ArrayRequest extends BaseRequest {

    public ArrayRequest (Context context, String url, JSONObject params, String option) {
        super(context, url, params, option);
    }

    public ArrayRequest (ListenerRequest listener, Context context, String url, JSONObject params, String option) {
        super(listener, context, url, params, option);
    }

    public void returnControl(JSONArray response) {

        BaseActivity controller = null;

        if(getListener() != null){
            getListener().callbackArrayResults(response, getOption());
            return;
        }

        else if (getContext().getClass().getSimpleName().equals("LoginActivity")) {
            controller = (LoginActivity) getContext();
        }
        /*else if (getContext().getClass().getSimpleName().equals("MasterActivity")) {
            controller = (MasterActivity) getContext();
        }*/

        if ( controller != null ) {
            if (getOption() == "ItemsMenu") {

            } else {
                controller.getArrayResults(response, getOption());
            }
        }
    }

    public void makeRequest() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String urlQuery = getUrlQuery();

        Log.d("holi", urlQuery);

        JsonArrayRequest getRequest =new JsonArrayRequest(urlQuery, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        returnControl(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("holi", error.toString());
            }
        }
        );
        queue.add(getRequest);
    }
}
