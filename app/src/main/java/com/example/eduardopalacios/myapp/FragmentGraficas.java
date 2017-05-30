package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Ely'z on 29/05/2017.
 */

public class FragmentGraficas extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_graficas);

        Button btn,btn1;

        btn = (Button) findViewById(R.id.button);
        btn1 = (Button) findViewById(R.id.button1);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_click(v);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_click1(v);
            }
        });




    }

    private void btn_click(View sender){
        Intent i = new Intent(this, FragmentInformeGastos.class);
        startActivity(i);


    }
    private void btn_click1(View sender){
        Intent i = new Intent(this, FragmentInformeAhorro.class);
        startActivity(i);


    }

}
