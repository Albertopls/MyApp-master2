package com.example.eduardopalacios.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
 * Use the {@link FragmentGastos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentGastos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText ed_cantidad;

    TextView nuevo_informe, nombre_informe;

    Button boton_mostrarInformes, boton_GuardarGasto, boton_mas;

    ListView lista_categoria, lista_cantidad;
    TextView text_nuevoinforme2;

    Spinner spinner_categoria, spinner_informes;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterList_cantidad, adapterList_categoria;




    List<String> contenido_lista_cantidad = new ArrayList<String>();
    List<String> contenido_lista_categoria = new ArrayList<String>();
    List<String> informes = new ArrayList<String>();


    private OnFragmentInteractionListener mListener;

    public FragmentGastos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentGastos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentGastos newInstance(String param1, String param2) {
        FragmentGastos fragment = new FragmentGastos();
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
        View view= inflater.inflate(R.layout.fragment_fragment_gastos, container, false);
        final PreferenciasUsuario prefid= new PreferenciasUsuario(getActivity());
        final int id_usuario=prefid.cargar_userid();
        inicializarComponentes(view);
        cambiar_letra();


        //Spinner categoria

        String contenido_categoria[]={"Entretenimiento", "Comida", "Trabajo", "Transporte", "Otros"};
        adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, contenido_categoria);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_categoria.setAdapter(adapter);

       //Botones

        //Boton mostrar informes

        boton_mostrarInformes.setOnClickListener(new View.OnClickListener() {
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
                            Toast numero3 = Toast.makeText(getContext(),"Informes cargados",Toast.LENGTH_SHORT);
                            numero3.show();



                            JSONArray Informes_response = jsonResponse.getJSONArray("informes");



                            for (int i = 0; i < Informes_response.length(); i++) {
                                String a = Informes_response.get(i).toString();
                                //Extraer solo digitos
                                String soloCuenta= extractNumbers_Letters(a);

                                if (a != null) {
                                    informes.add(soloCuenta);
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                };//Termina de devolver valores

                //Spinners



                ConsultarInformesRequest ConsultarInfomeRequest = new ConsultarInformesRequest(id_usuario, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(ConsultarInfomeRequest);


                informes.add("Selecciona un informe");
                ArrayAdapter<String> Array_cuentas = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, informes );
                Array_cuentas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner_informes.setAdapter(Array_cuentas);


                //}



            }
        });


        //Boton más
        boton_mas.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View v) {
                /*

                if (validaciones(ed_cantidad)) {


                    final double cantidad = Double.parseDouble(ed_cantidad.getText().toString());
                    final String categoria = spinner_categoria.getSelectedItem().toString();
                    String nombre = spinner_informes.getSelectedItem().toString();

                    //Consulta id_informe
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        Toast numero3 = Toast.makeText(getContext(), "Consultar_id", Toast.LENGTH_SHORT);

                        @Override
                        public void onResponse(String response) {
                            boolean exito = false;

                            try {


                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    String idinforme = jsonResponse.getString("id_Informe_gastos");


                                    int id_informe = Integer.parseInt(idinforme);
                                    prefid.escribePreferencia_idinforme(id_informe);

                                    //Insertar gastos en informe

                                    Response.Listener<String> responseListener = new Response.Listener<String>() {

                                        Toast numero3 = Toast.makeText(getContext(), "Informes cargados", Toast.LENGTH_SHORT);
                                        final ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                                                "Loading. Please wait...", true);

                                        @Override
                                        public void onResponse(String response) {


                                            try {


                                                JSONObject jsonResponse = new JSONObject(response);
                                                boolean success = jsonResponse.getBoolean("success");


                                                if (success) {
                                                    dialog.dismiss();
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                    builder.setMessage("Gasto registrado");
                                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {

                                                            dialog.dismiss();

                                                        }
                                                    });
                                                    AlertDialog dialog = builder.create();
                                                    dialog.show();
                                                    String nombre = spinner_categoria.getSelectedItem().toString();
                                                    String cantidad = ed_cantidad.getText().toString().trim();

                                                    contenido_lista_cantidad.add(cantidad);
                                                    contenido_lista_categoria.add(nombre);

                                                    adapterList_cantidad = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, contenido_lista_cantidad);
                                                    lista_cantidad.setAdapter(adapterList_cantidad);

                                                    adapterList_categoria = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, contenido_lista_categoria);
                                                    lista_categoria.setAdapter(adapterList_categoria);


                                                    ed_cantidad.setText("");
                                                    spinner_categoria.setId(0);


                                                } else {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                    builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();

                                            }

                                        }

                                    };


                                    GastosRequest registerRequest = new GastosRequest(cantidad, categoria, id_informe, responseListener);
                                    RequestQueue queue = Volley.newRequestQueue(getContext());
                                    queue.add(registerRequest);


                                } else {

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }


                        }

                    };


                    Id_InformeRequest informeRequest = new Id_InformeRequest(nombre, id_usuario, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    queue.add(informeRequest);

                }


*/



                if (validaciones(ed_cantidad)) {
                    final int id_informe= prefid.cargar_idinforme();

                    double cantidad= Double.parseDouble(ed_cantidad.getText().toString());
                    String categoria= spinner_categoria.getSelectedItem().toString();

                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        final ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                                "Loading. Please wait...", true);

                        @Override
                        public void onResponse(String response) {


                            try {


                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    dialog.dismiss();
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("Gasto registrado");
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();

                                        }
                                    });
                                    AlertDialog dialog=builder.create();
                                    dialog.show();
                                    String nombre = spinner_categoria.getSelectedItem().toString();
                                    String cantidad = ed_cantidad.getText().toString().trim();

                                    contenido_lista_cantidad.add(cantidad);
                                    contenido_lista_categoria.add(nombre);

                                    adapterList_cantidad= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, contenido_lista_cantidad);
                                    lista_cantidad.setAdapter(adapterList_cantidad);

                                    adapterList_categoria= new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, contenido_lista_categoria);
                                    lista_categoria.setAdapter(adapterList_categoria);




                                    ed_cantidad.setText("");
                                    spinner_categoria.setId(0);



                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                    };


                    GastosRequest registerRequest = new GastosRequest(cantidad,categoria, id_informe, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    queue.add(registerRequest);

                    }





            }

        });


        //Boton Guardar gasto

        boton_GuardarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void
            onClick(View v) {




                /*
                else {
                    Toast toast=Toast.makeText(getActivtiy (),"error in confirm password",Toast.LENGTH_SHORT);
                    toast.show();
                }
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

    public void inicializarComponentes(View view){
        spinner_categoria = (Spinner)view.findViewById(R.id.spn_categoria);
        spinner_informes= (Spinner) view.findViewById(R.id.spinner_editar_informe);
        ed_cantidad= (EditText) view.findViewById(R.id.edit_cantidad);
        boton_GuardarGasto= (Button) view.findViewById(R.id.Button_GuardarGasto);
        boton_mas= (Button) view.findViewById(R.id.button_m);
        boton_mostrarInformes= (Button) view.findViewById(R.id.button_mostrar_informes);
        lista_categoria= (ListView) view.findViewById(R.id.list_categoria);
        lista_cantidad= (ListView) view.findViewById(R.id.list_cantidad);
        text_nuevoinforme2= (TextView)view.findViewById(R.id.text_nuevoinforme2);

    }
    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Langdon.otf");
        text_nuevoinforme2.setTypeface(face);

    }


    public boolean validaciones(EditText ed_cantidad )
    {
        boolean dato=true;

            if(ed_cantidad.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "No ingresaste una cantidad", Toast.LENGTH_SHORT).show();

            }

        return dato;
    }

    public String extractNumbers_Letters(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c) || Character.isAlphabetic(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

}
