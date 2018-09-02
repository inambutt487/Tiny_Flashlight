package com.jalsawasiapps.bright.flashlight;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

public class Fullscreen extends Activity {
    String videopath;
    VideoView videoView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        videoView = (VideoView) findViewById(R.id.fullvideoView);
        imageView = (ImageView) findViewById(R.id.full_imgview);
        Intent mIntent = getIntent();
        int intValue = mIntent.getIntExtra("data", 0);
        if(intValue==11){
            Tab1();
            imageView.setImageResource(R.drawable.flash);
        }
        else if(intValue==10){
            Tab1();
        }
        else if(intValue==21){
            Tab2();
            imageView.setImageResource(R.drawable.world);
        }
        else if(intValue==20){
            Tab2();
        }
        else if(intValue==31){
            Tab3();
            imageView.setImageResource(R.drawable.fire);
        }
        else if(intValue==30) {
            Tab3();
        }
        else if(intValue==41){
            Tab4();
            imageView.setImageResource(R.drawable.trouch);
        }
        else if(intValue==40) {
            Tab4();
        }
        else if(intValue==51){
            Tab5();
            imageView.setImageResource(R.drawable.rainbow);
        }
        else if(intValue==50) {
            Tab5();
        }
        else {}


    }
    public void Tab1(){
        videopath =  "android.resource://com.example.emaan.project2"+"/"+R.raw.tab1;
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
    }

    public void Tab2(){
        videopath =  "android.resource://com.example.emaan.project2"+"/"+R.raw.tab2;
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
    }

    public void Tab3(){
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
    }

    public void Tab4(){
        videopath =  "android.resource://com.example.emaan.project2"+"/"+R.raw.tab4;
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
    }

    public void Tab5(){
        videopath =  "android.resource://com.example.emaan.project2"+"/"+R.raw.tab5;
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
    }
}