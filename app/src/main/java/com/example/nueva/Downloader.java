package com.example.nueva;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,Object> {


    Context context;
    String urlAddress;
    GridView listView;
    ProgressDialog progressDialog;

    public Downloader(Context context, String urlAddress, GridView listView) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.listView = listView;

    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Recuperar items");
        progressDialog.setMessage("Recuperando... Por favor espera");
        progressDialog.show();

    }

    @Override
    protected Object doInBackground(Void... voids) {
        //System.out.print("En el doInBa");

        return this.downloadData();
    }

    @Override
    protected void onPostExecute(Object data) {
        super.onPostExecute(data);
        progressDialog.dismiss();

        if(data.toString().startsWith("Error en Post")){
            Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show();
        }else {
            //Parse Rss
            //new RSSParser(context,(InputStream) data,listView).execute();
        }
    }

    private Object downloadData(){

        Object coneccion = Conector.connect(urlAddress);

        if (coneccion.toString().startsWith("Error")){
            return coneccion.toString();
        }

        try{

            HttpURLConnection con=(HttpURLConnection)coneccion;
            int reponseCode=con.getResponseCode();
            Log.d("respuesta antes OK ", String.valueOf(reponseCode));

            if (reponseCode==con.HTTP_OK){
                Log.d("respuesta en el OK ", String.valueOf(reponseCode));
                InputStream is= new BufferedInputStream(con.getInputStream());
                return is;
            }

            return ErrorTraker.RESPONSE_ERROR+con.getResponseMessage();
        }catch (IOException e){
            e.printStackTrace();
            return ErrorTraker.IO_ERROR;
        }

    }
}
