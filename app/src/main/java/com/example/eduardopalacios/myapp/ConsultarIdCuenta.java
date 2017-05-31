package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidag on 30/05/2017.
 */

public class ConsultarIdCuenta extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/ConsultarIdCuenta.php";
    private Map<String, String> params;

    public ConsultarIdCuenta(int numero_tarjeta, Response.Listener<String>listener)

    {
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("numero_tarjeta", numero_tarjeta+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
