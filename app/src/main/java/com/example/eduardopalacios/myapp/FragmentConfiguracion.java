package com.example.eduardopalacios.myapp;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.SharedPreferencesCompat;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ely'z on 25/05/2017.
 */

public class FragmentConfiguracion extends Fragment {

    Toolbar mToolbar;
    Button mRedColor;
    Button mBlueColor;
    Button mYellowColor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private void storeColor(int color){
       SharedPreferences msharedPreferences = getContext().getSharedPreferences("ToolbarColor",MODE_PRIVATE);
        SharedPreferences.Editor mEditor = msharedPreferences.edit();
        mEditor.putInt("color", color);
        mEditor.apply();
    }

    private int getColor(){
        SharedPreferences msharedPreferences = getContext().getSharedPreferences("ToolbarColor",MODE_PRIVATE);
        int selectedColor = msharedPreferences.getInt("color",getResources().getColor(R.color.colorPrimary));
        return selectedColor;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_configuracion, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRedColor = (Button) view.findViewById(R.id.btnred);
        mBlueColor = (Button) view.findViewById(R.id.btnblue);
        mYellowColor = (Button) view.findViewById(R.id.btnyellow);

       if(getColor() != getResources().getColor(R.color.colorPrimary)){
            mToolbar.setBackgroundColor(getColor());
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
               getActivity().getWindow().setStatusBarColor(getColor());
            }
        }

        mRedColor.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                  getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
               }
               storeColor(getResources().getColor(R.color.colorRed));
            }
        });

        mBlueColor.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                   getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
                }
               storeColor(getResources().getColor(R.color.colorBlue));
            }

        });

        mYellowColor.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                mToolbar.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                   getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorYellow));
                }
               storeColor(getResources().getColor(R.color.colorYellow));
            }
        });
        return view;
    }


}
