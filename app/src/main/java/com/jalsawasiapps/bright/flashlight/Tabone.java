package com.jalsawasiapps.bright.flashlight;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class Tabone extends Fragment{

    int value1=0;
    int value2=0;
    Button icon_button,play_button,full_button;
    ImageView icon_imageview;
    VideoView videoView;
    String videopath;
    public View rootView;
    Intent fullintent;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tabone, container, false);
        tab2Light();
        return rootView;
    }
    @Override
    public void onResume() {
        videoView.start();
        super.onResume();

    }

    public void tab2Light(){
        icon_imageview = (ImageView)rootView.findViewById(R.id.icon_imgview);
        icon_imageview.setImageResource(R.drawable.flash);
        icon_imageview.setVisibility(rootView.VISIBLE);

        icon_button = (Button) rootView.findViewById(R.id.icon_btn);
        icon_button.setBackgroundResource(R.drawable.eyeoff);

        play_button = (Button) rootView.findViewById(R.id.play_btn);
        play_button.setBackgroundResource(R.drawable.pause);

        full_button = (Button) rootView.findViewById(R.id.full_btn);
        full_button.setBackgroundResource(R.drawable.viewoff);

        videoView = (VideoView)rootView.findViewById(R.id.videoView);
        videopath =  "android.resource://com.jalsawasiapps.super.bright.led.tiny.flashlight"+"/"+R.raw.tab1;

        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        icon_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value2==0) {
                    icon_button.setBackgroundResource(R.drawable.eyeon);
                    icon_imageview.setVisibility(rootView.INVISIBLE);
                    value2=1;
                }
                else{
                    icon_button.setBackgroundResource(R.drawable.eyeoff);
                    icon_imageview.setVisibility(rootView.VISIBLE);
                    value2=0;
                }
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(value1==0) {
                    play_button.setBackgroundResource(R.drawable.play);
                    videoView.pause();
                    value1 = 1;

                }
                else{
                    play_button.setBackgroundResource(R.drawable.pause);
                    videoView.start();
                    value1=0;
                }
                }

       });

        full_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullintent = new Intent(rootView.getContext(),Fullscreen.class);
               if(value2==0){
                   fullintent.putExtra("data",11);
                   startActivity(fullintent);
               }
                else {
                   fullintent.putExtra("data",10);
                   startActivity(fullintent);
               }
            }
        });
    }
}
