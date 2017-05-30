package com.example.eduardopalacios.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/*import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet; */
import java.util.ArrayList;




/**
 * Created by Ely'z on 29/05/2017.
 */

public class FragmentInformeAhorro extends AppCompatActivity {
  /*  private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragment_informeahorro);

        pieChart = (PieChart) findViewById(R.id.pieChart); */

        /*definimos algunos atributos*/
    /*    pieChart.setHoleRadius(40f);
        pieChart.setDrawYValues(true);
        pieChart.setDrawXValues(true);
        pieChart.setRotationEnabled(true);
        pieChart.animateXY(1500, 1500);
*/
		/*creamos una lista para los valores Y*/
      /*  ArrayList<Entry> valsY = new ArrayList<Entry>();
        valsY.add(new Entry(5,0));
        valsY.add(new Entry(10,1));
        valsY.add(new Entry(15,2));
        valsY.add(new Entry(5,3));
        valsY.add(new Entry(5,4));
        valsY.add(new Entry(10,5));
        valsY.add(new Entry(15,6));
        valsY.add(new Entry(5,7));
        valsY.add(new Entry(10,8));
        valsY.add(new Entry(10,9));
        valsY.add(new Entry(5,10));
        valsY.add(new Entry(5,11)); */

 		/*creamos una lista para los valores X*/
        ArrayList<String> valsX = new ArrayList<String>();
       /* valsX.add("Enero");
        valsX.add("Febrero");
        valsX.add("Marzo");
        valsX.add("Abril");
        valsX.add("Mayo");
        valsX.add("Junio");
        valsX.add("Julio");
        valsX.add("Agosto");
        valsX.add("Septiembre");
        valsX.add("Octubre");
        valsX.add("Noviembre");
        valsX.add("Diciembre"); */

 		/*creamos una lista de colores*/
        ArrayList<Integer> colors = new ArrayList<Integer>();
     /*   colors.add(Color.rgb(0,128,255));
        colors.add(Color.rgb(255,128,0));
        colors.add(Color.rgb(255,120,79));
        colors.add(Color.rgb(37,120,30));
        colors.add(Color.rgb(20,10,8));
        colors.add(Color.rgb(80,50,84));
        colors.add(Color.rgb(255,4,8));
        colors.add(Color.rgb(37,8,10));
        colors.add(Color.rgb(0,180,200));
        colors.add(Color.rgb(255,200,7));
        colors.add(Color.rgb(0,190,60));
        colors.add(Color.rgb(50,1,79));
*/

 		/*seteamos los valores de Y y los colores*/
     /*   PieDataSet set1 = new PieDataSet(valsY, "Resultados");
        set1.setSliceSpace(3f);
        set1.setColors(colors); */

		/*seteamos los valores de X*/
   /*     PieData data = new PieData(valsX, set1);
        pieChart.setData(data);
        pieChart.highlightValues(null);
        pieChart.invalidate(); */

        /*Ocutar descripcion*/
       // pieChart.setDescription("");
        /*Ocultar leyenda*/
     //   pieChart.setDrawLegend(false);
    }

