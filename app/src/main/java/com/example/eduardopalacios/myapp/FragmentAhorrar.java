package com.example.eduardopalacios.myapp;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


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
    Spinner Spinner_cuenta, Spinner_cargo, Spinner_años;

    ArrayAdapter<String> adapter;

    //BOTONES
    Button boton_guardar, button_calcular;
    Button button_llenar;
    //EditText
    EditText Edittext_meta;
    //TextView
    TextView Textview_cantidad;
    TextView textView_titulo_ahorrar;
    PreferenciasUsuario prefs_id;
    Spinner spinner_cuentas;
    List<String> cuentasBancarias = new ArrayList<String>();


    private OnFragmentInteractionListener mListener;


    public FragmentAhorrar() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public FragmentAhorrar newInstance(String param1, String param2) {

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

    }//Termina Oncreate

    @Override
    public void onStart() {
        super.onStart();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_ahorrar, container, false);
        inicializar_componentes(view);
        spinner_cuentas = (Spinner) view.findViewById(R.id.Spinner_cuenta);
        button_llenar= (Button) view.findViewById(R.id.button_llenar);
        prefs_id= new PreferenciasUsuario(this.getActivity());
        final int id_usuario= prefs_id.cargar_userid();





//
        String [] contenido_cargo={"1","2","3","6"};
        adapter= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, contenido_cargo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Spinner_cargo.setAdapter(adapter);


        String [] contenido_tiempo=new String[]{"1", "2", "3"};
        adapter= new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, contenido_tiempo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Spinner_años.setAdapter(adapter);

        //Botones

        //Boton calcular

        button_calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular_cantidad();


            }
        });


        //Boton cargar sppiner
        button_llenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Valores de cuenta
                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {




                        boolean exito = false;
                        String id = null;

                        try {


                            JSONObject jsonResponse = new JSONObject(response);
                            Toast numero3 = Toast.makeText(getContext(),"Cuentas cargadas",Toast.LENGTH_SHORT);
                            numero3.show();



                            JSONArray Cuenta_response = jsonResponse.getJSONArray("cuentas");



                            for (int i = 0; i < Cuenta_response.length(); i++) {
                                String a = Cuenta_response.get(i).toString();
                                        //Extraer solo digitos
                                String soloCuenta= extractDigits(a);

                                if (a != null) {
                                    cuentasBancarias.add(soloCuenta);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                };//Termina de devolver valores

                //Spinners



                ConsultarCuentaRequest ConsultarCuentaRequest = new ConsultarCuentaRequest(id_usuario, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(ConsultarCuentaRequest);


                cuentasBancarias.add("Selecciona tu cuenta");
                ArrayAdapter<String> Array_cuentas = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cuentasBancarias );
                Array_cuentas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner_cuentas.setAdapter(Array_cuentas);


                //}



            }
        });

        //Boton guardar e ir a la siguiente actividad
        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pregunta

                /*AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("DATOS AHORRO");
                builder.setMessage("¿Deseas guardar?");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    */

                        //Método de guardar registro


                        int num_cuenta = Integer.parseInt(spinner_cuentas.getSelectedItem().toString());
                        final double meta_ahorro = Float.parseFloat(Edittext_meta.getText().toString());
                        final int cargo = Integer.parseInt(Spinner_cargo.getSelectedItem().toString());
                        final int tiempo = Integer.parseInt(Spinner_años.getSelectedItem().toString());
                        final double cantidad_descuento=calcular_cantidad();


                        Response.Listener<String> responseListener = new Response.Listener<String>() {


                            @Override
                            public void onResponse(String response) {


                                try {


                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");


                                    if (success) {
                                        String id_cuenta = jsonResponse.getString("id_cuenta");
                                        prefs_id.escribePreferencia_id_cuenta(Integer.parseInt(id_cuenta));

                                        int cuenta_id=prefs_id.cargar_id_cuenta();


                                        Response.Listener<String> responseListener = new Response.Listener<String>() {


                                            @Override
                                            public void onResponse(String response) {


                                                try {


                                                    JSONObject jsonResponse = new JSONObject(response);
                                                    boolean success = jsonResponse.getBoolean("success");


                                                    if (success) {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                        builder.setMessage( "Ahorro agregado").setNegativeButton("Retry", null).create().show();



                                                    } else {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                        builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();

                                                }

                                            }

                                        };


                                        InsertarAhorro registerRequest = new InsertarAhorro(meta_ahorro, cargo, tiempo, cantidad_descuento, cuenta_id, responseListener);
                                        RequestQueue queue = Volley.newRequestQueue(getContext());
                                        queue.add(registerRequest);




                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();

                                }

                            }

                        };


                        ConsultarIdCuenta registerRequest = new ConsultarIdCuenta(num_cuenta, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        queue.add(registerRequest);



                /*
                }
                });



                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });
                AlertDialog dialog=builder.create();
                dialog.show();
                */

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




    //Métodos


    public void inicializar_componentes(View view){
        textView_titulo_ahorrar = (TextView)view.findViewById(R.id.textView_titulo_ahorrar);
        Spinner_cargo= (Spinner)view.findViewById(R.id.Spinner_cargo);
        Spinner_años= (Spinner)view.findViewById(R.id.Spinner_años);
        Edittext_meta=(EditText)view.findViewById(R.id.editText_meta);
        Textview_cantidad=(TextView)view.findViewById(R.id.textView_cantidad);
        button_calcular= (Button)view.findViewById(R.id.button_calc);
        boton_guardar = (Button)view.findViewById(R.id.Button3);
    }


    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Langdon.otf");

        textView_titulo_ahorrar.setTypeface(face);

    }
    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    public boolean validaciones()
    {
        boolean dato=true;

        if(spinner_cuentas.getSelectedItem().toString()=="Selecciona tu cuenta")
        {
            dato=false;
            Toast.makeText(getContext(), "Se necesita seleccionar una cuenta", Toast.LENGTH_SHORT).show();

        }

        return dato;
    }

 public double calcular_cantidad(){
     int meta= Integer.parseInt(Edittext_meta.getText().toString());
     int cargo= Integer.parseInt(Spinner_cargo.getSelectedItem().toString());
     int años= Integer.parseInt(Spinner_años.getSelectedItem().toString());
     int meses;
     int res;
     double cantidad =0.0;
     switch (años){
         case 1: meses=12;
             res= meses/cargo;
             cantidad= meta/res;
             break;
         case 2: meses=24;
             res= meses/cargo;
             cantidad= meta/res;
             break;
         case 3: meses=36;
             res= meses/cargo;
             cantidad= meta/res;
             break;
     }

     Textview_cantidad.setText("$ "+cantidad);
     return cantidad;

 }



}
