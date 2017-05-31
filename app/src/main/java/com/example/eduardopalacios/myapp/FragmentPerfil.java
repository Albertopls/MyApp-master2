package com.example.eduardopalacios.myapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
 * Use the {@link FragmentPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPerfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    private TextView textViewUsername, textViewUserEmail;
    public static final String TAG="THEMES";

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button button_llenar;
    Spinner spinner_cuentas;


    List<String> cuentasBancarias = new ArrayList<String>();

    private OnFragmentInteractionListener mListener;

    public FragmentPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPerfil newInstance(String param1, String param2) {
        FragmentPerfil fragment = new FragmentPerfil();
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
        View view = inflater.inflate(R.layout.fragment_fragment_perfil, container, false);
        inicializar_componentes(view);

        //Nombre de usuario, apellido, email
        final PreferenciasUsuario prefs_id= new PreferenciasUsuario(getActivity());


        String nombre= prefs_id.cargar_nombreusuario();
        String apellido = prefs_id.cargar_last_name();
        String email = prefs_id.cargar_email();

        textViewUsername.setText(nombre+ " " +apellido);
        textViewUserEmail.setText(email);

        //Cuentas
        button_llenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //Valores de cuenta

         int id_usuario= prefs_id.cargar_userid();
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


        cuentasBancarias.add("Mis cuentas");
        ArrayAdapter<String> Array_cuentas = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cuentasBancarias );
        Array_cuentas.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner_cuentas.setAdapter(Array_cuentas);


        //}



    }
});



        return view;
    }

    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
        //toggleTheme();
    }
/*
    private void toggleTheme() {

        // Following options to change the Theme must precede setContentView().

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        String myName = sharedPref.getString("edittext_preference", "");


        // Convert name to all upper case if that preference checked
        String temp = "Hello " + myName;
        if (textViewUsername != null) textViewUsername.setText(temp);

        Log.i(TAG, "MainActivity:  Name=" + myName);
    }

*/


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

    public void inicializar_componentes(View view){
        spinner_cuentas= (Spinner) view.findViewById(R.id.spinner_perfil);
        button_llenar=(Button) view.findViewById(R.id.button_perfil);
        textViewUsername = (TextView) view.findViewById(R.id.textViewUsername);
        textViewUserEmail = (TextView) view.findViewById(R.id.textViewUserEmail);
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
}
