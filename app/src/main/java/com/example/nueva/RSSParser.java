package com.example.nueva;

import android.util.Log;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class RSSParser {


    private static final String ns = null;

    public ArrayList<Item> parseRSS(InputStream in) throws XmlPullParserException,IOException{

        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readRSS(parser);
        } finally {
            in.close();
        }

    }


    private ArrayList<Item> readRSS(XmlPullParser parser) throws XmlPullParserException,IOException{
        ArrayList<Item> items = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "rss");

        int event =parser.getEventType();

        Log.e("name","lalalalalaaaaaaaa");
        items.clear();

        Item itemAudio= new Item();



        parser.nextTag();
        String tagName = parser.getName();

        Log.e("name NNNN",tagName);
        Log.e("EVENT", String.valueOf(event));

        int eventz;
        String atibuto="";
        int conta=0;
        int i=0,j=0;
        do{
            switch (event){
                case XmlPullParser.START_TAG:
                    if (parser.getName().equals("item")){

                        // Log.e("name", String.valueOf(conta)+" ITEM");

                        itemAudio=new Item();
                    }if (parser.getName().equals("title")) {
                    // Log.e("title", parser.getName()+" ITEM");
                    String resultado = "";

                    if (parser.next() == XmlPullParser.TEXT) {
                        resultado = parser.getText();
                        parser.nextTag();
                    }
                    itemAudio.setTitulo(resultado);
                    // Log.e("tituloooo", itemAudio.getTitulo());
                    conta=1;
                }else if (parser.getName().equals("enclosure")){
                    atibuto = parser.getAttributeValue(0);
                    // Log.e("atributoooo", atibuto);
                    itemAudio.setEnclosure(atibuto);
                    //Log.d("cancionnnnnn", itemAudio.getEnclosure());

                    i=1;
                }
                    break;

            }

            event=parser.next();


            if (conta==1 && i==1){
                //  Log.d("itemmm ", String.valueOf(j));
                j++;
                i=0;
                conta=0;
                items.add(itemAudio);
            }

        }while (event!=XmlPullParser.END_DOCUMENT);

        return items;
    }




}
