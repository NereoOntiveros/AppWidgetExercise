package com.example.appwidgetsample;

import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listaPalabras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creaPalabras();
        enviaPalabras(this);

    }

    public void creaPalabras(){
        listaPalabras.add("Mesa");
        listaPalabras.add("Ordenador");
        listaPalabras.add("Silla");
        listaPalabras.add("Piano");
    }

    public void enviaPalabras(Context context){

        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        intent.setComponent(new ComponentName(context,NewAppWidget.class));
        intent.putExtra("palabras",listaPalabras);
        context.sendBroadcast(intent);

    }
}