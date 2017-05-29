package com.example.eduardopalacios.myapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.opengl.EGLDisplay;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentNuevoInforme.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentNuevoInforme#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNuevoInforme extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button boton_crearInforme;

    EditText edit_nuevoinforme;

    private OnFragmentInteractionListener mListener;

    public FragmentNuevoInforme() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNuevoInforme.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNuevoInforme newInstance(String param1, String param2) {
        FragmentNuevoInforme fragment = new FragmentNuevoInforme();
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
        View view= inflater.inflate(R.layout.fragment_fragment_nuevo_informe, container, false);
        inicializar(view);
        final PreferenciasUsuario prefs_id= new PreferenciasUsuario(getActivity());





       boton_crearInforme.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
           //Guardar informe

            final String nombre= edit_nuevoinforme.getText().toString();

            if (validaciones(edit_nuevoinforme)) {
                //Toast toast =Toast.makeText(getContext(),"entro",Toast.LENGTH_SHORT);
                //toast.show();

                final int user_id= prefs_id.cargar_userid();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                            "Loading. Please wait...", true);


                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");




                            if (success) {
                                dialog.dismiss();

                                Response.Listener<String> responseListener = new Response.Listener<String>(){

                                    @Override
                                    public void onResponse(String response) {
                                        boolean exito=false;

                                        try {


                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");


                                            if (success) {
                                                String idinforme = jsonResponse.getString("id_Informe_gastos");
                                                String nombre_informe_gastos = jsonResponse.getString("nombre_informe");



                                                int id_informe=Integer.parseInt(idinforme);
                                                prefs_id.escribePreferencia_idinforme(id_informe);
                                                prefs_id.escribePreferencia_nombre_informe(nombre_informe_gastos);


                                                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                                fragmentManager.beginTransaction().replace(R.id.content_navigationdrawer, new FragmentGastos()).commit();

                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setMessage(" Informe  "+prefs_id.cargar_nombre_informe()+"  Creado");
                                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {

                                                       dialog.dismiss();

                                                    }
                                                });
                                                AlertDialog dialog=builder.create();
                                                dialog.show();



                                            } else {

                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();

                                        }




                                    }

                                };


                                Id_InformeRequest informeRequest = new Id_InformeRequest(nombre, user_id, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getContext());
                                queue.add(informeRequest);





                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }

                };


               NuevoInformeRequest registerRequest = new NuevoInformeRequest(nombre, user_id, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(registerRequest);


            }

            //Empezar nueva actividad

            //getActivity().getActionBar().setTitle("Agregar Gastos");


        }
    });


        return view ;
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


    public void inicializar(View view){
        boton_crearInforme=(Button)view.findViewById(R.id.Button_nuevoinforme);
        edit_nuevoinforme=(EditText)view.findViewById(R.id.edit_nuevo_informe);
    }



    public boolean validaciones(EditText nombre_informe)
    {
        boolean dato=true;

            if(nombre_informe.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "You did not enter a name", Toast.LENGTH_SHORT).show();

            }

        return dato;
    }




}
