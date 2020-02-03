package com.example.android.proyecto.AppDataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.proyecto.Dao.CardDao;

import com.example.android.proyecto.Dao.DeckDao;
import com.example.android.proyecto.models.Card;
import com.example.android.proyecto.models.Deck;

@Database(entities = {Card.class,Deck.class}, version = 2, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract CardDao cardDAO();
    public abstract DeckDao deckDAO();


    private static DataBaseRoom INSTANCE=null;


    public static DataBaseRoom getInstance(final Context context){

        if (INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(), DataBaseRoom.class, "lista_compra.db").fallbackToDestructiveMigration().build();
        }

        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE=null;
    }
}
