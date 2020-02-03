package com.example.android.proyecto.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cards")
public class Card implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long autoId;

    @ColumnInfo(name="idDeck")
    private long idDeck;

    @ColumnInfo(name="battleID")
    private String id;

    @ColumnInfo(name="name")
    private String name;

    @ColumnInfo(name="text")
    private String text;

    @ColumnInfo(name="cost")
    private int cost;

    @ColumnInfo(name="attack")
    private int attack;

    @ColumnInfo(name="health")
    private int health;

    @ColumnInfo(name="cardClass")
    private String cardClass;

    @ColumnInfo(name="ratity")
    private String ratity;

    @Ignore
    private final static String URLbase="https://art.hearthstonejson.com/v1/render/latest/esES/256x/";

    @ColumnInfo(name="url")
    private String url;

    @ColumnInfo(name="doble")
    private Boolean doble=false;





    public Card(){}

    public Card(String id, String name, String text, int cost,int attack, int health, String cardClass, String rarity ) {
        this.id=id;
        this.name=name;
        this.text=text;
        this.cost=cost;
        this.attack=attack;
        this.health=health;
        this.cardClass=cardClass;
        this.ratity=rarity;
        CrearUrl(id);
    }


    //GETTER, SETTER y TOSTRING:
    public String getId() {
        return id;
    }
    public String getName() {
        return name ;
    }
    public String getText() {
        return text;
    }
    public int getCost() {
        return cost;
    }
    public int getAttack() {
        return attack;
    }
    public int getHealth() {
        return health;
    }
    public String getCardClass() {
        return cardClass;
    }
    public String getRatity() {
        return ratity;
    }

    public void setId(String id) {this.id = id;}
    public void setName(String name) { this.name = name;}
    public void setText(String text) {this.text = text; }
    public void setCost(int cost) {this.cost = cost; }
    public void setAttack(int attack) {this.attack = attack;}
    public void setHealth(int health) {this.health = health;}
    public void setCardClass(String cardClass) {this.cardClass = cardClass;}
    public void setRatity(String ratity) {this.ratity = ratity;}
    public void setUrl(String url) {this.url = url;}

    public String getUrl() { return url; }
    public Boolean getDoble() {return doble;}
    public void setDoble(Boolean doble) {this.doble = doble;}

    public long getAutoId() {return autoId;}
    public void setAutoId(long autoId) {this.autoId = autoId;}

    public long getIdDeck() {return idDeck;}
    public void setIdDeck(long idDeck) {this.idDeck = idDeck;}

    @Override
    public String toString() {
        return "Card{" +
                "autoId=" + autoId +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", cost=" + cost +
                ", attack=" + attack +
                ", health=" + health +
                ", cardClass='" + cardClass + '\'' +
                ", ratity='" + ratity + '\'' +
                ", url='" + url + '\'' +
                ", doble=" + doble +
                '}';
    }

    //CREAR URL con el url base y el id
    private void CrearUrl(String id) {

        this.url=""+URLbase+id+".png";

    }

}
