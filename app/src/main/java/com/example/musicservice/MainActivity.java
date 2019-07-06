package com.example.musicservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MusicStopListiner {


    ImageView ivPlayStop;
    String audiolink="https://dl.dropbox.com/home?preview=Numb20Official20Video20-20Linkin20Park-kXYiU_JCYtU.mp3";
    boolean musicPlaying=false;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPlayStop=findViewById(R.id.ivPlayStop);
        ivPlayStop.setBackgroundResource(R.drawable.play);
        serviceIntent=new Intent(this,MyplayService.class);
        ApplicationClass.context=(Context)MainActivity.this;

        ivPlayStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!musicPlaying){
                    playAudio();
                    ivPlayStop.setImageResource(R.drawable.stop);
                    musicPlaying=true;
                }
                else {
                    stopPlayService();
                    ivPlayStop.setImageResource(R.drawable.play);
                    musicPlaying=false;

                }

            }
        });
    }

    private void stopPlayService() {

        try {
            stopService(serviceIntent);

        }
        catch (SecurityException e)
        {
            Toast.makeText(this,"ERROR" +e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void playAudio() {
        serviceIntent.putExtra("AudioLink",audiolink);
        try {
            startService(serviceIntent);

        }
        catch (SecurityException e)
        {
            Toast.makeText(this,"ERROR" +e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onMusicStopped() {
        ivPlayStop.setImageResource(R.drawable.play);
        musicPlaying=false;
    }
}
