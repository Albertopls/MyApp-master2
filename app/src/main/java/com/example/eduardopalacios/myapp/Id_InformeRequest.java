package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardopalacios on 25/03/17.
 */

public class Id_InformeRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/ConsultarIdInforme.php";
    private Map<String, String> params;

    public Id_InformeRequest(String nombre_informe, int user_user_id, Response.Listener<String>listener)
    {
        super(Method.POST,LOGIN_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("nombre_informe", nombre_informe);
        params.put("user_user_id", user_user_id+ "");


    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}

