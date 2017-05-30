package com.example.eduardopalacios.myapp;

import android.graphics.Color;
/*
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet; */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Ely'z on 29/05/2017.
 */

public class FragmentInformeGastos extends AppCompatActivity {
  /*  private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gastos);

        pieChart = (PieChart) findViewById(R.id.pieChart); */

        /*definimos algunos atributos*/
        /*
        pieChart.setHoleRadius(40f);
        pieChart.setDrawYValues(true);
        pieChart.setDrawXValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1500, 1500);
       */
		/*creamos una lista para los valores Y*/
     /*   ArrayList<Entry> valsY = new ArrayList<Entry>();
        valsY.add(new Entry(20,0));
        valsY.add(new Entry(30,1));
        valsY.add(new Entry(40,2));
        valsY.add(new Entry(10,3));
   */
 		/*creamos una lista para los valores X*/
       /* ArrayList<String> valsX = new ArrayList<String>();
        valsX.add("Entretenimiento");
        valsX.add("Comida");
        valsX.add("Transporte");
        valsX.add("Otros Gastos"); */



 		/*creamos una lista de colores*/
       /* ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(Color.rgb(0,128,255));
        colors.add(Color.rgb(255,128,0));
        colors.add(Color.rgb(255,120,79));
        colors.add(Color.rgb(37,120,79));


 		/*seteamos los valores de Y y los colores*/
    /*    PieDataSet set1 = new PieDataSet(valsY, "Resultados");
        set1.setSliceSpace(3f);
        set1.setColors(colors); */

		/*seteamos los valores de X*/
      /*  PieData data = new PieData(valsX, set1);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate(); */

        /*Ocutar descripcion*/
      /*  pieChart.setDescription("");
        /*Ocultar leyenda*/
        // pieChart.setDrawLegend(false);
    }

