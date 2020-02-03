package com.example.android.proyecto.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.proyecto.models.Deck;

import java.util.List;

@Dao
public interface DeckDao {

    @Insert
    public long insertDeck(Deck deck);

    @Delete
    public int delete(Deck deck);

    @Query("SELECT * FROM decks")
    public LiveData<List<Deck>> getAllDecks();

    @Query("SELECT * FROM decks WHERE id=:id")
    public LiveData<List<Deck>> getDeckWithID(int id);




}

