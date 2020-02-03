package com.example.android.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OpcionesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opciones_layout);


    }


    public void CrearOnClick(View view) {
        Intent intent = new Intent(this, ElegirClaseActivity.class);
        startActivity(intent);
    }

    public void VerOnClick(View view) {
        Intent intent = new Intent(this, VerMazosActivity.class);
        startActivity(intent);
    }
}
