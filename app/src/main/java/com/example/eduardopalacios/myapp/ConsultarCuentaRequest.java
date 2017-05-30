package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidag on 28/05/2017.
 */

public class ConsultarCuentaRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/ConsultarCuenta.php";
    private Map<String, String> params;

    public ConsultarCuentaRequest(int id_usuario, Response.Listener<String>listener)
    {
        super(Method.POST,LOGIN_REQUEST_URL,listener,null);
        params=new HashMap<>();
        params.put("user_user_id", id_usuario+"");


    }

    @Override
    public Map<String, String> getParams() {

        return params;
    }
}
