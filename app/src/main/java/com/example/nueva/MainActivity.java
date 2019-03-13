package com.example.nueva;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;


import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final static String urlAddress="https://www.ivoox.com/podcast-cloud-jazz-smooth-jazz_fg_f127170_filtro_1.xml";

    private ArrayList<Item> entries = null;

    private ApadtadorItems apadtadorItems;

    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        entries = new ArrayList();



        new DownloadXmlTask().execute(urlAddress);


        Log.d("aqui tamooo", String.valueOf(entries.size()));

    }

    private class DownloadXmlTask extends AsyncTask<String, Void, ArrayList<Item>> {

        @Override
        protected ArrayList<Item> doInBackground(String... strings) {
            try {
                return loadXmlFromNetwork(strings[0]);
            } catch (IOException e) {
                return null;
            } catch (XmlPullParserException e) {
                return null;
            }
        }

        protected void onPostExecute(ArrayList<Item> result) {
            setContentView(R.layout.activity_main);
            // Displays the HTML string in the UI via a WebView

            listView = findViewById(R.id.listaaa);
            Log.d("a que hora", String.valueOf(result.size()));
            ArrayList<Item> listaaa = result;
            Log.e("tam de listaa", String.valueOf(listaaa.size()));
            TextView textView = findViewById(R.id.titulo);


            //textView.setText(listaaa.get(0).titulo.toString());

            apadtadorItems = new ApadtadorItems(getApplication().getApplicationContext(),listaaa,R.layout.activity_main);
            listView.setAdapter(apadtadorItems);

            Log.d("sale?", String.valueOf(result.size()));


        }

        private ArrayList<Item> loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {

            InputStream stream = null;
            // Instantiate the parser
            RSSParser parser = new RSSParser();



            String titulo = null;
            String audio = null;


            try {
                stream = downloadUrl(urlString);
                entries = parser.parseRSS(stream);
                // Makes sure that the InputStream is closed after the app is
                // finished using it.
            } finally {
                if (stream != null) {
                    stream.close();
                }
            }

            Log.d("tam ", String.valueOf(entries.size()));

            return entries;
        }

        private InputStream downloadUrl(String urlString) throws IOException {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            return conn.getInputStream();
        }
    }
}
