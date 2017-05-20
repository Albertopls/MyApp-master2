package com.example.eduardopalacios.myapp;

import android.text.Editable;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardopalacios on 15/05/17.
 */

public class TarjetaRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/cuenta.php";
    private Map<String, String> params;

    public TarjetaRequest(String  banco, int cvc, String fecha_vigencia, double cantidad_tarjeta,int numero_tarjeta, int valor, Response.Listener<String>listener)
    {
        super(Request.Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("banco", banco);
        params.put("cvp", cvc+"");
        params.put("vigencia", fecha_vigencia);
        params.put("cantidad_tarjeta", cantidad_tarjeta+"");
        params.put("numero_tarjeta", numero_tarjeta+"");
        params.put("user_user_id", valor+"");


    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
