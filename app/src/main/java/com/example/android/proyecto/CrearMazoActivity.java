package com.example.android.proyecto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.android.proyecto.fragments.CardsFragment;
import com.example.android.proyecto.fragments.ListFragment;
import com.example.android.proyecto.models.Card;

import java.util.ArrayList;

public class CrearMazoActivity extends AppCompatActivity implements CardsFragment.OnCardSelected {

    private String claseEscogida;
    private ArrayList<Card> cartasSeleccionadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_mazo);

        cartasSeleccionadas=new ArrayList<Card>();

        Intent intent = getIntent();
        if (intent != null) {
            claseEscogida = (String) intent.getStringExtra("claseEscogida");

            CardsFragment cf = (CardsFragment) getSupportFragmentManager().findFragmentById(R.id.card_fragment);
            cf.setClase(claseEscogida);      //Le pasa la clase seleccionada anteriormente


            ListFragment lf = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
            //lf.setClaseMazo(claseEscogida);
            lf.setCartasIntroducidas(cartasSeleccionadas);

            /*se le manda desde aquí vacío en lugar de crearla vacia directamente en el fragment para despues poder reutilizar éste
            cuando se le pase una otra lista cuando se deseen ver los mazos, pero en esa ocasión irá la lista llena*/

        }








    }

    @Override
    public void clickCard(Card carta) {
        ListFragment lf= (ListFragment) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        lf.renderCard(carta,claseEscogida);
    }

   // @Override
    public void callbackListaCartasSeleccionadas(ArrayList<Card> cartas) {
        cartasSeleccionadas=cartas;
    }

}
