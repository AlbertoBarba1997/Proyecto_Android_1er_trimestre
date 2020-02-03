package com.example.android.proyecto;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.android.proyecto.ADAPTERS.CmAdapter;
import com.example.android.proyecto.ADAPTERS.DeckAdapter;
import com.example.android.proyecto.ADAPTERS.ListAdapter;
import com.example.android.proyecto.ViewModel.CardViewModel;
import com.example.android.proyecto.ViewModel.DeckViewModel;
import com.example.android.proyecto.models.Card;
import com.example.android.proyecto.models.Deck;

import java.util.ArrayList;
import java.util.List;

public class MostrarMazo extends AppCompatActivity {
    long idDeck;
    Deck deck;
    RecyclerView rv;
    CmAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    CardViewModel modelCard;
    List<Card> cartas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_mazo);


        Intent intent = getIntent();
        if (intent != null) {
            idDeck =(Long) intent.getLongExtra("idDeck", idDeck);
        }

        rv=(RecyclerView) this.findViewById(R.id.recyclerViewMostrarMazo);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        cartas=new ArrayList<>();

        adapter=new CmAdapter(cartas, R.layout.item_row_cm, MostrarMazo.this, new CmAdapter.OnClickListener() {
            @Override
            public void onClick(Card carta, int pos) {

            }

        });
        rv.setAdapter(adapter);





        //Obtener cartas del deck

        modelCard=ViewModelProviders.of(this).get(CardViewModel.class);
        modelCard.getCardsWithIdDeck(idDeck).observe(this,new Observer<List<Card>>(){
            @Override
            public void onChanged(@Nullable List<Card> cartas) {

                adapter.addCard(cartas);


            }

        });



    }

    public void AtrasClick(View view) {
        Intent intent = new Intent(this, OpcionesActivity.class);
        startActivity(intent);
    }
}
