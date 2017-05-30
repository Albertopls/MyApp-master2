package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Adriana on 05/05/2017.
 */

public class GastosRequest extends StringRequest{

    private static final String GASTOS_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/Gastos.php";
    private Map<String, String> params;

    public GastosRequest(double cantidad,String categoria, int id_informe_gastos, Response.Listener<String>listener)
    {
        super(Method.POST, GASTOS_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("cantidad_gastos", cantidad+ "");
        params.put("categoria", categoria);
        params.put("Informe_gastos_id_Informe_gastos", id_informe_gastos+ "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}


