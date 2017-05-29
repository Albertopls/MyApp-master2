package com.example.eduardopalacios.myapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAgregarTarjeta.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAgregarTarjeta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAgregarTarjeta extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView titulo;
    PreferenciasUsuario prefid;

    private OnFragmentInteractionListener mListener;

    public FragmentAgregarTarjeta() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentAgregarTarjeta.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAgregarTarjeta newInstance(String param1, String param2) {
        FragmentAgregarTarjeta fragment = new FragmentAgregarTarjeta();
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
        View view=inflater.inflate(R.layout.activity_agregar_tarjeta, container, false);
        prefid= new PreferenciasUsuario(getActivity());
        titulo= (TextView)view.findViewById(R.id.text_nuevacuenta);
        final EditText num_tarjeta =(EditText)view.findViewById(R.id.edit_nombre);
        final EditText cvc=(EditText)view.findViewById(R.id.edit_pesos);
        final EditText fecha =(EditText)view.findViewById(R.id.edit_fecha);
        final EditText cantidad=(EditText)view.findViewById(R.id.edit_cantidad);


        final Button btnaceptar=(Button)view.findViewById(R.id.Btnguardar);
        final Spinner lista=(Spinner)view.findViewById(R.id.spiner_fecha);
        final String[] datos={"Bancomer","City Banamex","HSBC","Banorte","ScotiaBank","Santander"};

        cambiar_letra();
        /*

        final Bundle bundle = getIntent().getExtras();
        String valores = bundle.getString("ide");
        final String valor_2=bundle.getString("boleano");

*/
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,datos);
        lista.setAdapter(adaptador);



        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaciones(num_tarjeta, cvc, fecha,cantidad) ) {
                    final int id_user= prefid.cargar_userid();
/*
                    boolean valor_nav;
                    valor_nav=Boolean.parseBoolean(valor_2);
                    String valor = null;

                    if(!valor_nav)
                    {

                        Bundle bundle2 = getIntent().getExtras();
                        valor = bundle2.getString("valor");
                    }
                    else
                    {
                        valor=bundle.getString("ide");

                    }

¨*/
                    int i = lista.getSelectedItemPosition();
                    String banco = datos[i];
                    //final int id_user = Integer.parseInt(valor);
                    int cvp = Integer.parseInt(cvc.getText().toString());
                    String fecha1 = fecha.getText().toString();
                    double cantidad_tarjeta = Double.parseDouble(cantidad.getText().toString());
                    int numero_tarjeta=Integer.parseInt(num_tarjeta.getText().toString());

                    Response.Listener<String> responseListener = new Response.Listener<String>() {


                        @Override
                        public void onResponse(String response) {


                            try {


                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");


                                if (success) {
                                    FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.content_navigationdrawer, new FragmentInicio()).commit();
                                    getActivity().getActionBar().setTitle("Inicio");

                                   /*
                                    String valor_id= String.valueOf(id_user);
                                    boolean opcion=true;
                                    String opcions=String.valueOf(opcion);
                                    Intent i = new Intent(AgregarTarjeta.this, Navigationdrawer.class);


                                    i.putExtra("identificador_id", valor_id);
                                    i.putExtra("identificador_boolean",opcions);


                                   startActivity(i);
                                   */

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                    builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                    };


                    TarjetaRequest registerRequest = new TarjetaRequest(banco, cvp, fecha1, cantidad_tarjeta,numero_tarjeta, id_user, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(getContext());
                    queue.add(registerRequest);


                }
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


    public boolean validaciones(EditText num_tarjeta, EditText cvc, EditText fecha, EditText cantidad)
    {
        boolean dato=true;

        if(cvc.getText().toString().trim().length()==0  && fecha.getText().toString().trim().length()==0 && cantidad.getText().toString().trim().length()==0 &&num_tarjeta.getText().toString().trim().length()==0)
        {
            dato=false;
            Toast.makeText(getContext(), "Empty form", Toast.LENGTH_SHORT).show();



        }
        else
        {
            if(num_tarjeta.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "You did not number of card", Toast.LENGTH_SHORT).show();

            }
            if(cvc.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "You did not enter cvp", Toast.LENGTH_SHORT).show();

            }
            if(fecha.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "You did not enter date", Toast.LENGTH_SHORT).show();

            }
            if(cantidad.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(getContext(), "You did not enter amount", Toast.LENGTH_SHORT).show();

            }

        }

        return dato;
    }


    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Langdon.otf");

        titulo.setTypeface(face);

    }
}