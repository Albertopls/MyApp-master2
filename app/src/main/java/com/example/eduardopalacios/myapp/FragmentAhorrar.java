package com.example.eduardopalacios.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TabHost;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAhorrar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAhorrar extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    //SPINNERS
    Spinner spinner_meta, spinner_tiempo, spinner_cargo;

    ArrayAdapter <String> adapter;

    //BOTONES
    Button boton_guardar;
    ImageButton boton_porcentaje, boton_pesos;

    //EditText
    EditText ed_Porcentaje, ed_pesos;

    private OnFragmentInteractionListener mListener;



    public FragmentAhorrar() {
        // Required empty public constructor
    }



    // TODO: Rename and change types and number of parameters
    public  FragmentAhorrar newInstance(String param1, String param2) {
        FragmentAhorrar fragment = new FragmentAhorrar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_ahorrar, container, false);
        inicializar_componentes(view);

        //Spinners

        String contenido_meta []={"1000", "10000", "100000"};
        adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, contenido_meta);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_meta.setAdapter(adapter);


        String [] contenido_cargo={"Diario", "Mensual", "Anual"};
        adapter= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, contenido_cargo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_cargo.setAdapter(adapter);


        String [] contenido_tiempo=new String[]{"1 año", "2 años", "3 años"};
        adapter= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, contenido_tiempo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_tiempo.setAdapter(adapter);

        //Botones

        //Boton procentaje

        boton_porcentaje.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ed_Porcentaje.setEnabled(true);
                ed_Porcentaje.setFocusable(true);
                ed_Porcentaje.setEnabled(true);
                ed_Porcentaje.setFocusable(false);
            }
        });

        //Boton procentaje

        boton_pesos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ed_pesos.setEnabled(true);
                ed_pesos.setFocusable(true);
                ed_Porcentaje.setEnabled(false);

            }
        });



        //Boton guardar e ir a la siguiente actividad

        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AgregarTarjeta.class);
                startActivity(i);

            }
        });




        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void inicializar_componentes(View view){
        spinner_meta= (Spinner)view.findViewById(R.id.spn_1);
        spinner_cargo= (Spinner)view.findViewById(R.id.spn_2);
        spinner_tiempo= (Spinner)view.findViewById(R.id.spn_3);
        boton_porcentaje= (ImageButton)view.findViewById(R.id.Button_Porcentaje);
        boton_pesos= (ImageButton) view.findViewById(R.id.Button_Pesos);
        boton_guardar = (Button)view.findViewById(R.id.Button3);
        ed_Porcentaje= (EditText)view.findViewById(R.id.edit_percent);
        ed_pesos=(EditText) view.findViewById(R.id.edit_pesos);
   }
}
