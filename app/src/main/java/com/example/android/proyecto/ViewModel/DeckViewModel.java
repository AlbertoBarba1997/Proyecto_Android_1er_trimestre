package com.example.android.proyecto.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.android.proyecto.AppDataBase.DataBaseRoom;
import com.example.android.proyecto.fragments.ListFragment;
import com.example.android.proyecto.models.Card;
import com.example.android.proyecto.models.Deck;

import java.util.List;

public class DeckViewModel extends AndroidViewModel {

    private LiveData<List<Deck>> deckList;
    private static DataBaseRoom db;
    private long idDeck;


    public DeckViewModel(@NonNull Application application){
        super(application);
        db= DataBaseRoom.getInstance(application);
        deckList=db.deckDAO().getAllDecks();

    }


    public long getIdDeck() {return idDeck;}

    public LiveData<List<Deck>> getAllDecksVM(){
        return deckList;
    }

    public void addDeck(Deck deck){
        new DeckViewModel.AsyncAddDeckDB().execute(deck);
    }

    private class AsyncAddDeckDB  extends AsyncTask< Deck, Void, Long> {

        Deck deck;

        @Override
        protected Long doInBackground(Deck... decks) {

            long id = -1;

            if (decks.length != 0) {

                deck = decks[0];
                id = db.deckDAO().insertDeck(decks[0]);
                Log.d("Deck numero:", Long.toString(id));
                deck.setId(id);
                idDeck=id;


            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1) {
                System.out.println("ERROR AÑADIENDO DECK a la bd.");
            } else {
                System.out.println("DECK INSERTADO CORRECTAMENTE");
            }
        }
    }
}