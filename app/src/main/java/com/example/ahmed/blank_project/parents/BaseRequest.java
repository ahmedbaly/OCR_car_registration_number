package com.example.ahmed.blank_project.parents;

import android.content.Context;
import android.widget.Toast;

import com.example.ahmed.blank_project.interfaces.ListenerRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by Tecnew on 23/11/2016.
 */

public class BaseRequest {

    private String url;
    private JSONObject parameters;
    private Context context;
    private String option;
    private ListenerRequest listener;

    public BaseRequest (Context context, String url, JSONObject params, String option) {
        this.context = context;
        this.url = url;
        this.parameters = params;
        this.option = option;
    }

    public BaseRequest (ListenerRequest xlistener, Context context, String url, JSONObject params, String option) {
        this(context, url, params, option);
        this.listener = xlistener;
    }

    public String getUrlQuery() {
        String aux = this.url + "?";
        String key;

        if(this.parameters != null) {
            for(Iterator<String> keys = this.parameters.keys(); keys.hasNext();) {
                try {
                    key = keys.next();
                    aux = aux + key + "=" + this.parameters.getString(key) + "&";
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return aux;
    }

    // GET'S

    public Context getContext(){ return this.context; }

    public String getUrl() { return this.url; }

    public JSONObject getParameters() { return this.parameters; }

    public String getOption(){
        return this.option;
    }
    public ListenerRequest getListener() { return this.listener; }


    // **********************

    public void ToastMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
