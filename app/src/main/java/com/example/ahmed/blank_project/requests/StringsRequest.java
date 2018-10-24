package com.example.ahmed.blank_project.requests;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ahmed.blank_project.parents.BaseRequest;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Tecnew on 23/11/2016.
 */

public class StringsRequest extends BaseRequest {

    public StringsRequest (Context context, String url, JSONObject params, String option) {
        super(context, url, params, option);
    }

    public void returnControl(String response) {
      /*  if ( getOption().equals("ValidarDevice") ) {
            LoginActivity la = (LoginActivity) getContext();
            la.getArrayResults(response, getOption());
        }else if ( getOption().equals("SalvarMapear") ) {
            MasterActivity la = (MasterActivity) getContext();
            la.getArrayResults(response, getOption());
        }*/
    }

    public void getRequest() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String urlQuery = getUrlQuery();

        Log.d("holi", urlQuery);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlQuery,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ToastMsg("Done!");
                    returnControl(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error Holi", error.toString());
                }
            }
        );

        queue.add(stringRequest);
    }

    public void postRequest() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        final JSONObject jsonBody = getParameters();

        final String mRequestBody = jsonBody.toString();

        StringRequest sr = new StringRequest(Request.Method.POST, getUrl(),
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    ToastMsg("Done!");
                    returnControl(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    ToastMsg("Failed!");
                    Log.d("error Holi", error.toString());
                }
            }
        ) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=UTF-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                byte[] aux = null;
                try {
                    aux = mRequestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                            mRequestBody, "utf-8");
                }
                return aux;
            }
        };

        queue.add(sr);
    }
}
