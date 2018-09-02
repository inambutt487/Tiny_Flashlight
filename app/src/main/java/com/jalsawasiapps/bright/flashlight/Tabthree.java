package com.jalsawasiapps.bright.flashlight;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class Tabthree extends Tabone{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView3 = inflater.inflate(R.layout.tabthree, container, false);

        icon_imageview = (ImageView)rootView3.findViewById(R.id.icon_imgview);
        icon_imageview.setImageResource(R.drawable.fire);
        icon_imageview.setVisibility(rootView3.VISIBLE);

        icon_button = (Button) rootView3.findViewById(R.id.icon_btn);
        icon_button.setBackgroundResource(R.drawable.eyeoff);

        play_button = (Button) rootView3.findViewById(R.id.play_btn);
        play_button.setBackgroundResource(R.drawable.pause);

        full_button = (Button) rootView3.findViewById(R.id.full_btn);
        full_button.setBackgroundResource(R.drawable.viewoff);

        videoView = (VideoView)rootView3.findViewById(R.id.videoView);
        videopath =  "android.resource://com.example.emaan.project2"+"/"+R.raw.tab3;

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
                fullintent = new Intent(rootView3.getContext(),Fullscreen.class);
                if(value2==0){
                    fullintent.putExtra("data",31);
                    startActivity(fullintent);
                }
                else {
                    fullintent.putExtra("data",30);
                    startActivity(fullintent);
                }
            }
        });
        return rootView3;
    }
    @Override
    public void onResume() {
        videoView.start();
        super.onResume();

    }
}
