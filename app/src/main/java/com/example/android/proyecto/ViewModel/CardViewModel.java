package com.example.android.proyecto.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.proyecto.AppDataBase.DataBaseRoom;
import com.example.android.proyecto.models.Card;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private LiveData<List<Card>> cardList;
    private static DataBaseRoom db;
    private long idDeck;
    private LiveData<List<Card>> cardListFilter;




    public CardViewModel(@NonNull Application application){
        super(application);
        db= DataBaseRoom.getInstance(application);
        cardList=db.cardDAO().getAllCards();

    }
    /*public CardViewModel(@NonNull Application application,long idDeck){
        super(application);
        db= DataBaseRoom.getInstance(application);
        cardList=db.cardDAO().getCardWithId(idDeck);

    }*/

    public void setIdDeck(long idDeck) {
        this.idDeck = idDeck;
    }

    public LiveData<List<Card>> getAllCardsVM(){
        return cardList;
    }
    public LiveData<List<Card>> getCardsWithIdDeck(long idDeck){
        cardListFilter=db.cardDAO().getCardWithId(idDeck);
        return cardListFilter;
    }


    public void addCard(Card carta){
        new AsyncAddCardDB().execute(carta);
    }

    private class AsyncAddCardDB extends AsyncTask<Card, Void, Long>{

        Card carta;

        @Override
        protected Long doInBackground(Card... cards) {

            long id=-1;

            if(cards.length !=0){
                String name=cards[0].getName();
                System.out.println("Añadiendo..."+name);
                carta=cards[0];
                id=db.cardDAO().insertCard(cards[0]);
                carta.setAutoId(id);


            }

            return id;
        }
        @Override
        protected void onPostExecute(Long id){
            if(id == -1){
                System.out.println("ERROR ADDING");
            } else{
                System.out.println("CARTA AÑADIDA CORRECTAMENTE");
            }
        }
    }
}
