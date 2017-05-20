package com.example.eduardopalacios.myapp;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Adriana on 05/05/2017.
 */

public class GastosRequest extends StringRequest{

    private static final String GASTOS_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/Gastos.php";
    private Map<String, String> params;

    public GastosRequest(String cargo_gastos, String categoria, String cantidad, Response.Listener<String>listener)
    {
        super(Request.Method.POST, GASTOS_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("cargo_gastos", cargo_gastos);
        params.put("cantidad_gastos", cantidad);
        params.put("categoria", categoria);
        params.put("user_user_id", "1");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


