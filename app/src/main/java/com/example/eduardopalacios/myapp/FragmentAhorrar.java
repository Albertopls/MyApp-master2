package com.example.eduardopalacios.myapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
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
    Spinner Spinner_cuenta, Spinner_cargo, Spinner_años;

    ArrayAdapter<String> adapter;

    //BOTONES
    Button boton_guardar, button_calcular;
    //EditText
    EditText Edittext_meta;
    //TextView
    TextView Textview_cantidad;

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

        adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1);
        Spinner_cuenta.setAdapter(adapter);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_ahorrar, container, false);
        inicializar_componentes(view);
        spinner_cuentas = (Spinner) view.findViewById(R.id.Spinner_cuenta);
        prefs_id= new PreferenciasUsuario(this.getActivity());
        final int id_usuario= prefs_id.cargar_userid();
        //Spinners

        //Valores de cuenta
        Response.Listener<String> responseListener = new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                boolean exito = false;
                String id = null;

                try {

                    Log.e("TAG: json Response", response.toString());
                    JSONObject jsonResponse = new JSONObject(response);
                    //Toast numero3 = Toast.makeText(getContext(),"ya1",Toast.LENGTH_SHORT);
                    //numero3.show();

                    Log.e("TAG: json Response", jsonResponse.toString());

                    JSONArray Cuenta_response = jsonResponse.getJSONArray("cuentas");
                    Log.e("Arrays: ", Cuenta_response.toString());
                    //Toast numero2 = Toast.makeText(getContext(),"ya2",Toast.LENGTH_SHORT);
                    //numero2.show();

                    for (int i = 0; i < Cuenta_response.length(); i++) {
                        String a = Cuenta_response.get(i).toString();

                        Log.e("Contador i", "" + i + " - ");
                        if (a != null) {
                            cuentasBancarias.add(a);
                        }
                    }

                    Log.e("Tamanio lista", cuentasBancarias.size() + "");

                    Log.e("Valor:", Cuenta_response.get(2).toString());

                            /*for (int i = 0; Cuenta_response.length() < 1; i++) {
                                JSONObject c = Cuenta_response.getJSONObject(i);
                                //id = c.getString("numero_tarjeta");
                            }

                            Log.e("Array", Cuenta_response.toString());

                            Toast numero = Toast.makeText(getContext(),"ya3",Toast.LENGTH_SHORT);
                            numero.show();*/

                            /*    if (success) {


                                  // Toast numero = Toast.makeText(getContext(),"ya",Toast.LENGTH_SHORT);
                                    //numero.show();

                                //String usuario_id = jsonResponse.getString("user_id");
                                String numero_cuenta=jsonResponse.getString("numero_tarjeta");


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("Login Failed").setNegativeButton("Retry", null).create().show();
                            }*/
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        };//Termina de devolver valores

        if (cuentasBancarias !=null && cuentasBancarias.size()>0){
            ArrayAdapter<String> Array_cuentas = new ArrayAdapter<String>(getActivity(),R.layout.row_spinner_cuentas, cuentasBancarias );
        }


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
                //int cuenta= Integer.parseInt(Spinner_cuenta.getSelectedItem().toString());
                int meta= Integer.parseInt(Edittext_meta.getText().toString());
                int cargo= Integer.parseInt(Spinner_cargo.getSelectedItem().toString());
                int años= Integer.parseInt(Spinner_años.getSelectedItem().toString());
                int meses;
                int res;
                int cantidad = 0;
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

                Textview_cantidad.setText(""+cantidad);

                Response.Listener<String> responseListener = new Response.Listener<String>(){


                    @Override
                    public void onResponse(String response) {
                        boolean exito=false;
                        String id = null;


                    }

                };


                ConsultarCuentaRequest ConsultarCuentaRequest = new ConsultarCuentaRequest(id_usuario, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(ConsultarCuentaRequest);

            }
        });


        //Boton guardar e ir a la siguiente actividad
        boton_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pregunta
                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                builder.setTitle("DATOS AHORRO");
                builder.setMessage("¿Deseas guardar?");

                builder.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Método de guardar registro
                        int id_usuario= prefs_id.cargar_userid();

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
        Spinner_cuenta= (Spinner)view.findViewById(R.id.Spinner_cuenta);
        Spinner_cargo= (Spinner)view.findViewById(R.id.Spinner_cargo);
        Spinner_años= (Spinner)view.findViewById(R.id.Spinner_años);
        Edittext_meta=(EditText)view.findViewById(R.id.editText_meta);
        Textview_cantidad=(TextView)view.findViewById(R.id.textView_cantidad);
        button_calcular= (Button)view.findViewById(R.id.button_calc);
        boton_guardar = (Button)view.findViewById(R.id.Button3);
    }







}
