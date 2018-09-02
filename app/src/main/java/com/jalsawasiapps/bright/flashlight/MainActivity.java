package com.jalsawasiapps.bright.flashlight;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.startapp.android.publish.StartAppAd;
import com.startapp.android.publish.StartAppSDK;
import com.startapp.android.publish.splash.SplashConfig;
import com.wefika.horizontalpicker.HorizontalPicker;

public class MainActivity extends Activity implements HorizontalPicker.OnItemSelected, HorizontalPicker.OnItemClicked {

    private StartAppAd startapp = new StartAppAd(this);
    Thread timerThread;
    private int status; // Switch Indicator
    private int position; // Scroobar Indicator
    private String CM_Id;
    private CameraManager CM_Manager;
    private Button switch_button, compass_button, color_button, clean_button, compass_layout;
    private TextView pointer;
    HorizontalPicker picker;
    InterstitialAd mInterstitialAd;
    public static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        if (count == 0) {
            //               Developer ID    App ID
            StartAppSDK.init(this, "168784222", "208554077", false);
            StartAppAd.showSplash(this, savedInstanceState, new SplashConfig()
                    .setTheme(SplashConfig.Theme.GLOOMY).setLogo(R.drawable.icon)
                    .setAppName("Super Bright Led Tiny Flashlight : Torch Light"));
            count++;
        }

        setContentView(R.layout.activity_main);


        mInterstitialAd = new InterstitialAd(this);
        BannerAdmob();
        mInterstitialAd.setAdUnitId("ca-app-pub-3444255945927869/2537116081");
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });
        switch_button = (Button) findViewById(R.id.switch_btn);
        picker = (HorizontalPicker) findViewById(R.id.picker);
        picker.setOnItemClickedListener(this);
        picker.setOnItemSelectedListener(this);
        picker.setSelectedItem(3);
        pointer = (TextView) findViewById(R.id.pointertv);
        pointer.setBackgroundResource(R.drawable.point);
        CM_Manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            status = -1;
            CM_Id = CM_Manager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        switch_button.setBackgroundResource(R.drawable.off_button);
        compass_button = (Button) findViewById(R.id.compassbtn);
        compass_layout = (Button) findViewById(R.id.compass_btn2);
        color_button = (Button) findViewById(R.id.color_btn);
        compass_button.setBackgroundResource(R.drawable.compass);
//        compass_button.setRotation(-mAzimuth);
        clean_button = (Button) findViewById(R.id.clean_btn);
        flashLight();
//        start();
    }

    public void flashLight() {
        switch_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = status * -1;
                if (status == 1) {
                    try {
                        switch_button.setBackgroundResource(R.drawable.on_button);
                        turnOn();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (status == -1) {
                    try {
                        switch_button.setBackgroundResource(R.drawable.off_button);
                        turnOff();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        compass_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent intent = new Intent(MainActivity.this, Compass_Activity.class);
                startActivity(intent);
            }
        });

        color_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                Intent intent = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent);
            }
        });

        compass_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id="
                                + "com.jalsawasiapps.super.bright.led.tiny.flashlight")));
            }
        });
        clean_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://search?q=jalsawasi&c=apps")));
            }
        });
    }

    public void turnOn() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                CM_Manager.setTorchMode(CM_Id, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnOff() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                CM_Manager.setTorchMode(CM_Id, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void positiveScaleOne() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 4) {
                            turnOn();
                            sleep(2000);
                            turnOff();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void positiveScaleTwo() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 5) {
                            turnOn();
                            sleep(1000);
                            turnOff();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void positiveScaleThree() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 6) {
                            turnOn();
                            sleep(200);
                            turnOff();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void negativeScaleThree() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 0) {
                            turnOff();
                            sleep(2400);
                            turnOn();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void negativeScaleTwo() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 1) {
                            turnOff();
                            sleep(1500);
                            turnOn();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void negativeScaleOne() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 2) {
                            turnOff();
                            sleep(600);
                            turnOn();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }

    public void sosFunction() {
        timerThread = new Thread() {
            public void run() {
                try {
                    for (; ; ) {
                        if (status == 1 && position == 7) {
                            turnOn();
                            sleep(700);
                            turnOff();
                            turnOn();
                            turnOff();
                            turnOn();
                            turnOff();
                            turnOn();
                            turnOff();
                            sleep(1200);
                            turnOn();
                        } else {
                            turnOff();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timerThread.start();
    }


    @Override
    public void onItemClicked(int index) {
        position = picker.getSelectedItem();
        if (status == 1 && position == 0) {
            negativeScaleThree();
        } else if (status == 1 && position == 1) {
            negativeScaleTwo();
        } else if (status == 1 && position == 2) {
            negativeScaleOne();
        } else if (position == 3) {
            turnOn();
            status = 1;
            switch_button.setBackgroundResource(R.drawable.on_button);
        } else if (status == 1 && position == 4) {
            positiveScaleOne();
        } else if (status == 1 && position == 5) {
            positiveScaleTwo();
        } else if (status == 1 && position == 6) {
            positiveScaleThree();
        } else if (status == 1 && position == 7) {
            sosFunction();
        } else {
            Toast.makeText(MainActivity.this, "Flash Light Off", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(int index) {
        position = picker.getSelectedItem();
        if (status == 1 && position == 0) {
            negativeScaleThree();
        } else if (status == 1 && position == 1) {
            negativeScaleTwo();
        } else if (status == 1 && position == 2) {
            negativeScaleOne();
        } else if (position == 3) {
            turnOn();
            status = 1;
            switch_button.setBackgroundResource(R.drawable.on_button);
        } else if (status == 1 && position == 4) {
            positiveScaleOne();
        } else if (status == 1 && position == 5) {
            positiveScaleTwo();
        } else if (status == 1 && position == 6) {
            positiveScaleThree();
        } else if (status == 1 && position == 7) {
            sosFunction();
        } else {
            Toast.makeText(MainActivity.this, "Flash Light Off", Toast.LENGTH_SHORT).show();
        }
    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        super.onSensorChanged(event);
//        compass_button.setRotation(-mAzimuth);
//    }

    public void onshowAd() {
        startapp.showAd();
        startapp.loadAd();
    }

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Do You Want To Quit")

                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.icon)
                .show();
        startapp.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        startapp.onPause();
        turnOff();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startapp.onResume();
        if (status == 1) {
            turnOn();
        }
    }

    private void requestNewInterstitial() {
        // TODO Auto-generated method stub
        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);
    }

    private void BannerAdmob() {
        // TODO Auto-generated method stub
        AdView adView = (AdView) findViewById(R.id.adView8);
        adView.loadAd(new AdRequest.Builder().build());
    }
}