package com.example.eduardopalacios.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Build.*;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import android.os.StrictMode;
import org.json.JSONException;
import org.json.JSONObject;


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

    Button boton_GuardarGasto;

    Spinner spinner_categoria, spinner_periodo;
    ArrayAdapter<String> adapter;

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
        inicializarComponentes(view);

        //Spinner tiempo

        String contenido_periodo[]={"Dia", "Mes", "Año"};
        adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, contenido_periodo);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_periodo.setAdapter(adapter);


        //Spinner categoria

        String contenido_categoria[]={"Entretenimiento", "Comida", "Trabajo", "Transporte", "Otros"};
        adapter= new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, contenido_categoria);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_categoria.setAdapter(adapter);

        //Boton Guardar gasto

        boton_GuardarGasto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void
            onClick(View v) {
                String periodo= spinner_periodo.getSelectedItem().toString();
                String cantidad= ed_cantidad.getText().toString();
                String categoria= spinner_categoria.getSelectedItem().toString();
                int contador=0;

                Response.Listener<String> responseListener = new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {


                        try {


                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");




                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("Se registró el gasto").setNegativeButton("Retry", null).create().show();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage("No se registró el gasto").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                };

                if(is_empty()) {


                   cantidad= cantidad.substring(contador_espacios_cantidad());

                    GastosRequest registerRequest = new GastosRequest (periodo, categoria, cantidad, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    queue.add(registerRequest);
                }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    //Métodos

    public void inicializarComponentes(View view){
        spinner_periodo = (Spinner)view.findViewById(R.id.spn_periodo);
        spinner_categoria = (Spinner)view.findViewById(R.id.spn_categoria);
        ed_cantidad= (EditText) view.findViewById(R.id.edit_cantidad);
        boton_GuardarGasto= (Button) view.findViewById(R.id.Button_GuardarGasto);

    }


    public boolean is_empty()
    {

        boolean no_vacio=true;

        if(ed_cantidad.getText().toString().trim().length()==0)
        {
            no_vacio=false;
            Toast.makeText(getActivity(), "Introduce una cantidad", Toast.LENGTH_SHORT).show();

        }
        return no_vacio;
    }


    public int contador_espacios_cantidad()
    {
        int contador=0;
        for (int i=0;i<ed_cantidad.length();i++)
        {
            if (ed_cantidad.getText().toString().charAt(i)==' ')
            {

                contador++;
            }
        }
        return contador;
    }


}
