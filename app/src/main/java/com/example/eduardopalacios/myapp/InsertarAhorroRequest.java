package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidag on 30/05/2017.
 */

public class InsertarAhorroRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/InsertarAhorro.php";
    private Map<String, String> params;

    public InsertarAhorroRequest(int numero_tarjeta, float meta, int cargo_ahorro, int tiempo, float cantidad_descuento,Response.Listener<String>listener)

    {
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("numero_tarjeta", numero_tarjeta+"");
        params.put("meta", meta+"");
        params.put("cargo_ahorro", cargo_ahorro+"");
        params.put("tiempo", tiempo+"");
        params.put("cantidad_descuento", cantidad_descuento+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
