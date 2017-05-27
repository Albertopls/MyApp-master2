package com.example.eduardopalacios.myapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardopalacios on 15/05/17.
 */

public class NuevoInformeRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL ="https://bitchiest-core.000webhostapp.com/NuevoInforme.php";
    private Map<String, String> params;

    public NuevoInformeRequest(String nombre_informe, int valor, Response.Listener<String>listener)
    {
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        int totalGastos=0;
        params=new HashMap<>();
        params.put("nombre_informe", nombre_informe);
        params.put("total_gastos", totalGastos+"");
        params.put("user_user_id", valor+"");



    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
