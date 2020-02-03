package com.example.android.proyecto;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.proyecto.ADAPTERS.DeckAdapter;
import com.example.android.proyecto.ADAPTERS.ListAdapter;
import com.example.android.proyecto.ViewModel.DeckViewModel;
import com.example.android.proyecto.fragments.ListFragment;
import com.example.android.proyecto.models.Card;
import com.example.android.proyecto.models.Deck;

import java.util.ArrayList;
import java.util.List;

public class VerMazosActivity extends AppCompatActivity {

    DeckViewModel modelDeck;
    RecyclerView rv;
    List<Deck> decks;
    RecyclerView.LayoutManager layoutManager;
    DeckAdapter adapter;
    long idDeck;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_mazos_layout);

        Intent intent = getIntent();

        decks=new ArrayList<>();

        adapter=new DeckAdapter(decks, R.layout.item_row_mazo, VerMazosActivity.this, new DeckAdapter.OnClickListener(){
            @Override
            public void onClick(Deck deck, int pos) {
                Intent intent= new Intent(VerMazosActivity.this, MostrarMazo.class);

                idDeck=(long) deck.getId();

                intent.putExtra("idDeck",idDeck);
                startActivity(intent);


            }
        });

        rv=(RecyclerView) this.findViewById(R.id.recyclerViewMazos);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);


        modelDeck = ViewModelProviders.of(this).get(DeckViewModel .class);
        modelDeck.getAllDecksVM().observe(this,new Observer<List<Deck>>(){
                    @Override
                    public void onChanged(@Nullable List<Deck> decks) {

                        adapter.addDecks(decks);
                        System.out.println("HOOOOOOOOOOOOOOOOOOOOOOOOOOLAAAA HOLA HOLA HOLAAAA");
                        System.out.println(decks.get(0).toString());

                    }

                });







    }
    /*@Override
    public void clickDeck(Deck deck) {

    }*/
}
