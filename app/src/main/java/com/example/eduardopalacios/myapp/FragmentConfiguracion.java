package com.example.eduardopalacios.myapp;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    TextView textView_color;
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

        inicializar_componentes(view);

        try {
            if (getColor() != getResources().getColor(R.color.colorPrimary)) {
                mToolbar.setBackgroundColor(getColor());
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getActivity().getWindow().setStatusBarColor(getColor());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            mRedColor.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
                        }
                        storeColor(getResources().getColor(R.color.colorRed));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

            mBlueColor.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
                        }
                        storeColor(getResources().getColor(R.color.colorBlue));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });

            mYellowColor.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    mToolbar.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                    try {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.colorYellow));
                        }
                        storeColor(getResources().getColor(R.color.colorYellow));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return view;
    }

    public void inicializar_componentes(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mRedColor = (Button) view.findViewById(R.id.btnred);
        mBlueColor = (Button) view.findViewById(R.id.btnblue);
        mYellowColor = (Button) view.findViewById(R.id.btnyellow);
        textView_color= (TextView)view.findViewById(R.id.textView_color);

    }
    //Cambio de letra
    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Langdon.otf");

        textView_color.setTypeface(face);

    }

}