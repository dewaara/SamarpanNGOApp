package com.dewaara.samarpan;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;


public class WelcomeActivity extends AppCompatActivity  {


    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_welcome );

        // Set GIFImageView resourece

        gifImageView = (GifImageView) findViewById( R.id.gifImageView );

        try {
            InputStream inputStream = getAssets().open( "splash_screen.gif" );
            byte[] bytes = IOUtils.toByteArray( inputStream );
            gifImageView.setBytes( bytes );
            gifImageView.startAnimation();
        }
        catch (IOException ex) {

        }

        new Handler(  ).postDelayed( new Runnable() {
            @Override
            public void run() {
                WelcomeActivity.this.startActivity(new Intent( WelcomeActivity.this,MainActivity.class ));
                WelcomeActivity.this.finish();
            }
        } ,10000);

    }
}



