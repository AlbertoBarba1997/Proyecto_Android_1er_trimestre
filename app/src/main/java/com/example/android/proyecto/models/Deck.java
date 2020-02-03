package com.example.android.proyecto.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "decks")
public class Deck implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name="dclass")
    private String clasedeck;


    public Deck(){
    }
    public Deck(String clase){
        clasedeck=clase;
    }

    public String getClasedeck() {
        return clasedeck;
    }

    public void setClasedeck(String clasedeck) {
        this.clasedeck = clasedeck;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
