package com.example.ahmed.blank_project.requests;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ahmed.blank_project.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class UploadImage extends AsyncTask<Void, Void, String> {

    private String url;
    private JSONObject params;
    private Context context;
    private String photo;

    public UploadImage(Context context, String url, JSONObject params) {
        this.context = context;
        this.url = url;
        this.params = params;
    }

    public Context getContext() {
        return this.context;
    }

    public String getUrl() {
        return this.url;
    }

    public List<NameValuePair> getParams() {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        String key;
            if(this.params != null) {
                for(Iterator<String> keys = this.params.keys(); keys.hasNext();) {
                    try {
                        key = keys.next();
                        nvps.add(new BasicNameValuePair(key, this.params.getString(key)));
                        if (key == "foto") { photo = this.params.getString(key); }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
             }
        return nvps;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(Void... params) {
        String retorno = "";
        InputStream inputStream = null;
        HttpPost post = new HttpPost(getUrl());
        List<NameValuePair> nvps = getParams();

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse response = httpClient.execute(post);
            // receive response as inputStream
            inputStream = response.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                retorno = convertInputStreamToString(inputStream);
            else
                retorno = "Error on saving the photo";

        } catch (UnsupportedEncodingException e) {
            retorno = "Error - " + e.getMessage();

        } catch (IOException e) {
            retorno = "Error - " + e.getMessage();
        }

        Log.d("holi error", retorno);

        return retorno;
    }

    @Override
    protected void onPostExecute(String result) {
        String origem = this.url;
        //String wsMapearFoto = context.getResources().getString((R.string.SalvaMapearFoto));

        Log.d("holi", origem);

        /*if (origem.equals(wsMapearFoto)) {
            ((MasterActivity) getContext()).ConfirmarFoto(result + "-MapearFoto");
        }*/
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
