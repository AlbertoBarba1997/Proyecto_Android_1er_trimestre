package com.example.android.proyecto.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.proyecto.models.Card;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CardDao {

    @Insert
    public long insertCard(Card carta);

    @Delete
    public int deleteCard(Card carta);

    @Query("SELECT * FROM cards")
    public LiveData<List<Card>> getAllCards();

    @Query("SELECT * FROM cards WHERE idDeck =:id")
    public LiveData<List<Card>> getCardWithId(long id);




}
