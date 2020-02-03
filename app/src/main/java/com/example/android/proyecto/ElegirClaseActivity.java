package com.example.android.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ElegirClaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionarclase_layout);

        Intent intent = getIntent();

    }


    public void DruidaSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","DRUID");
        startActivity(intent);
    }

    public void SacerdoreSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","PRIEST");
        startActivity(intent);
    }

    public void BrujoSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","WARLOCK");
        startActivity(intent);

    }

    public void MagoSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","MAGE");
        startActivity(intent);
    }

    public void CazadorSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","HUNTER");
        startActivity(intent);
    }

    public void PicaroSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","ROGUE");
        startActivity(intent);

    }

    public void PaladinSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","PALADIN");
        startActivity(intent);

    }

    public void GuerreroSeleccionado(View view) {
        Intent intent= new Intent(ElegirClaseActivity.this, CrearMazoActivity.class);
        intent.putExtra("claseEscogida","WARRIOR");
        startActivity(intent);
    }
}

