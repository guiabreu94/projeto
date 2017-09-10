package com.lycha.example.augmentedreality;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.location.Location;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 09/09/2017.
 */

public class Pagina_principal extends Activity{

    ImageButton bcamera;
    ImageButton blista;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_inicial);
        bcamera = (ImageButton) findViewById(R.id.camera);
        blista = (ImageButton) findViewById(R.id.lista);

       bcamera.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){
            Intent intentLoadNewActivity = new Intent(Pagina_principal.this,ListDisplay.class);
               startActivity(intentLoadNewActivity);

           }
       });


        blista.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intentLoadNewActivity = new Intent(Pagina_principal.this,CameraViewActivity.class);
                startActivity(intentLoadNewActivity);


            }
        });


    }




}
