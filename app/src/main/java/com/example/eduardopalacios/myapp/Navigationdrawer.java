package com.example.eduardopalacios.myapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Navigationdrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, VerInforme.OnFragmentInteractionListener, FragmentInicio.OnFragmentInteractionListener, FragmentNuevoInforme.OnFragmentInteractionListener, FragmentPerfil.OnFragmentInteractionListener, FragmentGastos.OnFragmentInteractionListener, FragmentAhorrar.OnFragmentInteractionListener {

    PreferenciasUsuario prefid= new PreferenciasUsuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String first_name=prefid.cargar_nombreusuario();

        if(first_name==null)
        {

        }
        else
        {
            if(cargar_falso()) {

                Toast.makeText(this, "Welcome " + first_name, Toast.LENGTH_LONG).show();
            }
        }


        if(!cargar_falso())
        {

            Toast.makeText(this, "Welcome " + first_name, Toast.LENGTH_LONG).show();
        }




        DrawerLayout drawer2 = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle2 = new ActionBarDrawerToggle(this, drawer2, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer2.setDrawerListener(toggle2);
        toggle2.syncState();
        NavigationView navigationView2 = (NavigationView) findViewById(R.id.nav_view);
        navigationView2.setNavigationItemSelectedListener(this);

        //Agregar fracmento
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_navigationdrawer, new FragmentInicio()).commit();
        getSupportActionBar().setTitle("Inicio");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigationdrawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment= null;
        Boolean FragmentoSeleccionado= false;
        if (id == R.id.nav_inicio) {
            fragment= new FragmentInicio();
            FragmentoSeleccionado=true;
            getSupportActionBar().setTitle("Inicio");

        } else if (id == R.id.nav_camera) {
            fragment= new FragmentPerfil();
            FragmentoSeleccionado=true;
            getSupportActionBar().setTitle("Perfil");

        }else if (id == R.id.nav_gallery) {

            Bundle bundle = getIntent().getExtras();
            String opcions=bundle.getString("identificador_boolean");
            fragment= new FragmentAhorrar();
            FragmentoSeleccionado=true;
            getSupportActionBar().setTitle("Empieza a ahorrar");

        } else if (id == R.id.nav_slideshow) {
            fragment = new FragmentNuevoInforme();
            FragmentoSeleccionado = true;
            getSupportActionBar().setTitle("Mis gastos");


        }else if(id== R.id.nav_informe_mis_gastos){
            fragment = new VerInforme();
            FragmentoSeleccionado = true;
            getSupportActionBar().setTitle("Ver Informe Gastos");

        }else if(id== R.id.nav_manage){
            fragment = new FragmentConfiguracion();
            FragmentoSeleccionado = true;
            getSupportActionBar().setTitle("Configuración");

        } else if (id == R.id.reg_tarjeta) {
            /*
            Boolean opcion;
            Bundle bundle = getIntent().getExtras();
            String opcions=bundle.getString("identificador_boolean");
            opcion=Boolean.parseBoolean(opcions);

            if(opcion)
            {
                String valor;
                String valor_agregartarjeta;

                valor = bundle.getString("identificador_id");
                valor_agregartarjeta ="true";


*/
                Intent i =new Intent(Navigationdrawer.this,AgregarTarjeta.class);
               // i.putExtra("ide",valor);
                //i.putExtra("boleano",valor_agregartarjeta);
                startActivity(i);

/*
            }
            else {


                Intent intent2 = getIntent();
                String id_usuario = intent2.getStringExtra("identificador2");
                Intent i = new Intent(Navigationdrawer.this, AgregarTarjeta.class);
                i.putExtra("valor", id_usuario);
                startActivity(i);


                AgregarTarjeta agregar = new AgregarTarjeta();
                agregar.setVisible(true);

            }
            */

        } else if (id == R.id.nav_sign) {

            String cadena;
            Navigationdrawer.this.startActivity(new Intent(Navigationdrawer.this, login.class));
            Navigationdrawer.this.finish();
            String opcion = "false";
            try {

                FileOutputStream fos = openFileOutput("textFile.txt", MODE_PRIVATE);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                osw.write(opcion);
                osw.flush();
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if(FragmentoSeleccionado){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_navigationdrawer, fragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public boolean cargar_falso()
    {
        boolean verdadero=false;
        String s="";
        try {
            FileInputStream fis=openFileInput("textFile.txt");
            InputStreamReader isr= new InputStreamReader(fis);
            char[]inputBuffer= new char[100];

            int charRead;
            while ((charRead=isr.read(inputBuffer))>0)
            {
                String readString=String.copyValueOf(inputBuffer,0,charRead);
                s+=readString;
                inputBuffer=new char[100];
            }
            isr.close();

            verdadero=Boolean.parseBoolean(s);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return verdadero;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
