package com.example.eduardopalacios.myapp;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Adriana on 30/05/2017.
 */

public class InsertarAhorro extends StringRequest{

    private static final String REGISTER_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/InsertarAhorro.php";
    private Map<String, String> params;

    public InsertarAhorro(double meta, int cargo_ahorro, int tiempo, double cantidad_descuento, int id_cuenta, Response.Listener<String>listener)

    {
        super(Request.Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("meta", meta+"");
        params.put("cargo_ahorro", cargo_ahorro+"");
        params.put("tiempo", tiempo+"");
        params.put("cantidad_descuento", cantidad_descuento+"");
        params.put("id_cuenta", id_cuenta+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
