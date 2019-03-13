package com.example.nueva;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ApadtadorItems extends ArrayAdapter<Item> {

    private Context context;
    private ArrayList<Item> audios;
    private int layout;
    private MediaPlayer mediaPlayer;
    private boolean bandera=true;



    public ApadtadorItems(Context context, ArrayList<Item> audios, int layout) {
        super(context,layout,audios);
        Log.d("audiossss en adaptador", String.valueOf(audios.size()));
        this.context = context;
        this.audios = audios;
        this.layout = layout;
    }




    public class ViewHolder{


        ImageView imageView_reproducir, imageView_detener;

        private View convertView;
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        public ViewHolder(){
            convertView=layoutInflater.inflate(layout,null);
            //textView=convertView.findViewById(R.id.audio);

            imageView_reproducir=convertView.findViewById(R.id.image_reproducir);

            imageView_detener=convertView.findViewById(R.id.image_detener);

        }
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {



        Log.e("posicionnnnn ", String.valueOf(position));


        View view = LayoutInflater.from(context).inflate(
                R.layout.song_item, null);


        TextView textView = (TextView)view.findViewById(R.id.audio);

        final ImageView imageView_reproducir, imageView_detener;

        imageView_reproducir=view.findViewById(R.id.image_reproducir);

        imageView_detener=view.findViewById(R.id.image_detener);

        final Item item=audios.get(position);


        Log.e("itemmmmmm ", item.toString());

        textView.setText(item.toString());
        imageView_reproducir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (bandera){
                    mediaPlayer= (MediaPlayer) MediaPlayer.create(context, Uri.parse(item.enclosure));
                    bandera=false;
                }

                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imageView_reproducir.setImageResource(R.drawable.reproducir);
                }else{
                    mediaPlayer.start();
                    imageView_reproducir.setImageResource(R.drawable.detener);
                }

                mediaPlayer.start();
            }
        });

        imageView_detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bandera){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    bandera=true;
                }

                imageView_detener.setImageResource(R.drawable.reproducir);
            }
        });
        return view;
    }
}
