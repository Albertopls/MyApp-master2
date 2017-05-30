package com.example.eduardopalacios.myapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Consejo_Compras extends AppCompatActivity {
TextView title_consejo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejo__compras);
        title_consejo= (TextView) findViewById(R.id.title_consejo2);
        cambiar_letra();
    }


    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/Langdon.otf");
        title_consejo.setTypeface(face);

    }

}
