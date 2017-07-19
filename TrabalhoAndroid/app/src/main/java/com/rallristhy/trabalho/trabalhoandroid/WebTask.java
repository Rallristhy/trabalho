package com.rallristhy.trabalho.trabalhoandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rallristhy on 19/07/2017.
 */

public class WebTask extends AsyncTask<String, Void, String> {

    private Activity activity;

    private ProgressDialog progressDialog;

    WebTask (Activity activity){
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Buscando Informações...");
        progressDialog.setTitle("Aguarde...");
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(params[0])
                .build();

        try{
            Response response = client.newCall(request).execute();

            return response.body().string();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Log.d("teste", s);
        ((MapsActivity) (activity)).recebeJson(s);

        progressDialog.dismiss();
    }
}
