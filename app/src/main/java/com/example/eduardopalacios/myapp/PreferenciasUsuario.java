package com.example.eduardopalacios.myapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Adriana on 30/10/2015.
 */
public class PreferenciasUsuario {
    private Activity contexto;
    public PreferenciasUsuario (Activity contexto) {
        this.contexto = contexto;
    }


    public boolean escribePreferencia_userid(int user_id){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();

        try{

            editor.putInt("user_id", user_id);
            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int cargar_userid() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        int user_id = prefs.getInt("user_id", -1);


        return user_id;
    }

    public boolean escribePreferencia_user_user_id(int user_user_id){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();

        try{

            editor.putInt("user_user_id", user_user_id);
            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int cargar_user_user_id() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        int user_user_id = prefs.getInt("user_user_id", -1);


        return user_user_id;
    }

    public boolean escribePreferencia_nombreusuario(String first_name){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();
        try{
            editor.putString("first_name", first_name);

            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean escribePreferencia_email(String email){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();
        try{
            editor.putString("email", email);
            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean escribePreferencia_numTarjeta(String numero_tarjeta){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();
        try{
            editor.putString("numero_tarjeta", numero_tarjeta);

            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String cargar_nombreusuario() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        String first_name = prefs.getString("first_name", "");
        return first_name;
    }

    public String cargar_email() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        String email = prefs.getString("email", "");
        return email;
    }

    public String cargar_numTarjeta() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        String numero_tarjeta = prefs.getString("numero_tarjeta", "");
        return numero_tarjeta;
    }



    public boolean escribePreferencia_idinforme(int id_informe){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();
        try{
            editor.putInt("id_informe", id_informe);
            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public int cargar_idinforme() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        int id_informe = prefs.getInt("id_informe", -1);
        return id_informe;
    }

    public boolean escribePreferencia_nombre_informe(String nombre_informe){
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        SharedPreferences.Editor editor=prefs.edit();
        try{
            editor.putString("nombre_informe", nombre_informe);
            editor.apply();
            return true;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public String cargar_nombre_informe() {
        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(contexto.getBaseContext());
        String nombre_informe = prefs.getString("nombre_informe", "");
        return nombre_informe;
    }

}
