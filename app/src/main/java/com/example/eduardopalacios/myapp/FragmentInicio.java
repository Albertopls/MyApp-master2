package com.example.eduardopalacios.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInicio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInicio extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView titulo, consejo, consejo2, consejo3, consejo4;
    ImageView img_tarjetas, img_compras, img_metaahorro, img_ahorrar;;
   Button boton_detalles, boton_detalles2, boton_detalles3, boton_detalles4;

    private OnFragmentInteractionListener mListener;

    public FragmentInicio() {
        // Required empty public constructor
    }

   /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInicio.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInicio newInstance(String param1, String param2) {
        FragmentInicio fragment = new FragmentInicio();
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
        View view=inflater.inflate(R.layout.fragment_fragment_inicio, container, false);
        inicializar(view);
        cambiar_letra();



        boton_detalles.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(getActivity(), Consejo_Tarjetas.class);
           ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), img_tarjetas,"detalles");
            startActivity(intent, optionsCompat.toBundle());


        }
        });

        //Botones
        boton_detalles2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), Consejo_Compras.class);
                ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),img_compras,"detalles2");
                startActivity(intent, optionsCompat.toBundle());


            }
        });
        boton_detalles3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), Consejo_MetaAhorro.class);
                ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),img_metaahorro,"detalles3");
                startActivity(intent, optionsCompat.toBundle());


            }
        });
        boton_detalles4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), Consejo_TiempoAhorrar.class);
                ActivityOptionsCompat optionsCompat= ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),img_ahorrar,"detalles4");
                startActivity(intent, optionsCompat.toBundle());


            }
        });




        // Inflate the layout for this fragment
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


    //METODOS

    public void inicializar(View view){
        titulo= (TextView) view.findViewById(R.id.text_titulo);
        consejo= (TextView) view.findViewById(R.id.text_consejo1);
        consejo2=(TextView) view.findViewById(R.id.text_consejo2);
        consejo3=(TextView) view.findViewById(R.id.text_consejo3);
        consejo4=(TextView) view.findViewById(R.id.text_consejo4);
        img_tarjetas= (ImageView) view.findViewById(R.id.imageButton_tarjetas);
        img_compras=(ImageView) view.findViewById(R.id.imageButton_compra);
        img_metaahorro= (ImageView) view.findViewById(R.id.imageButton_metaahorro);
        img_ahorrar=(ImageView) view.findViewById(R.id.imageButton_ahorrar);
        boton_detalles=(Button) view.findViewById(R.id.button_detalles);
        boton_detalles2=(Button) view.findViewById(R.id.button_detalles2);
        boton_detalles3=(Button) view.findViewById(R.id.button_detalles3);
        boton_detalles4=(Button) view.findViewById(R.id.button_detalles4);

    }

    public void cambiar_letra(){
        Typeface face= Typeface.createFromAsset(getActivity().getAssets(),"fonts/Langdon.otf");
        Typeface face_consejo= Typeface.createFromAsset(getActivity().getAssets(),"fonts/KeepCalm-Medium.ttf");
        titulo.setTypeface(face);
        consejo.setTypeface(face_consejo);
        consejo2.setTypeface(face_consejo);
        consejo3.setTypeface(face_consejo);
        consejo4.setTypeface(face_consejo);
    }

}
