package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AgregarTarjeta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarjeta);

        final EditText num_tarjeta =(EditText)findViewById(R.id.edit_nombre);
        final EditText cvc=(EditText)findViewById(R.id.edit_pesos);
        final EditText fecha =(EditText)findViewById(R.id.edit_fecha);
        final EditText cantidad=(EditText)findViewById(R.id.edit_cantidad);


        final Button btnaceptar=(Button)findViewById(R.id.Btnguardar);
        final Spinner lista=(Spinner)findViewById(R.id.spiner_fecha);
        final String[] datos={"Bancomer","City Banamex","HSBC","Banorte","ScotiaBank","Santander"};

        final Bundle bundle = getIntent().getExtras();
        String valores = bundle.getString("ide");
        final String valor_2=bundle.getString("boleano");

        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,datos);
        lista.setAdapter(adaptador);




        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validaciones()) {

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


                    int i = lista.getSelectedItemPosition();
                    String banco = datos[i];
                    final int id_user = Integer.parseInt(valor);
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
                                    Intent intent = new Intent(AgregarTarjeta.this, Navigationdrawer.class);
                                    AgregarTarjeta.this.startActivity(intent);


                                    String valor_id= String.valueOf(id_user);
                                    boolean opcion=true;
                                    String opcions=String.valueOf(opcion);
                                    Intent i = new Intent(AgregarTarjeta.this, Navigationdrawer.class);


                                    i.putExtra("identificador_id", valor_id);
                                    i.putExtra("identificador_boolean",opcions);


                                   startActivity(i);

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(AgregarTarjeta.this);
                                    builder.setMessage("Register Failed").setNegativeButton("Retry", null).create().show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                            }

                        }

                    };


                    TarjetaRequest registerRequest = new TarjetaRequest(banco, cvp, fecha1, cantidad_tarjeta,numero_tarjeta, id_user, responseListener);
                    RequestQueue queue = Volley.newRequestQueue(AgregarTarjeta.this);
                    queue.add(registerRequest);


                }
            }
        });





    }
    public boolean validaciones()
    {
        boolean dato=true;
        EditText cvc=(EditText)findViewById(R.id.edit_pesos);
        EditText fecha =(EditText)findViewById(R.id.edit_fecha);
        EditText cantidad=(EditText)findViewById(R.id.edit_cantidad);
        EditText num_tarjeta =(EditText)findViewById(R.id.edit_nombre);

        if(cvc.getText().toString().trim().length()==0  && fecha.getText().toString().trim().length()==0 && cantidad.getText().toString().trim().length()==0 &&num_tarjeta.getText().toString().trim().length()==0)
        {
            dato=false;
            Toast.makeText(this, "Empty form", Toast.LENGTH_SHORT).show();



        }
        else
        {
            if(num_tarjeta.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(this, "You did not number of card", Toast.LENGTH_SHORT).show();

            }
            if(cvc.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(this, "You did not enter cvp", Toast.LENGTH_SHORT).show();

            }
            if(fecha.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(this, "You did not enter date", Toast.LENGTH_SHORT).show();

            }
            if(cantidad.getText().toString().trim().length()==0)
            {
                dato=false;
                Toast.makeText(this, "You did not enter amount", Toast.LENGTH_SHORT).show();

            }

        }

        return dato;
    }







}
