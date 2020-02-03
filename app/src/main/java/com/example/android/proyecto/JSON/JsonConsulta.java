package com.example.android.proyecto.JSON;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.proyecto.models.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonConsulta {


    private ArrayList<Card> cartasNeutrales;
    private ArrayList<Card> cartasClase;
    private JSONArray arrayJSON;



    public JsonConsulta(JSONArray arrayJSON) {

        cartasNeutrales = new ArrayList<Card>();
        cartasClase = new ArrayList<Card>();
        this.arrayJSON = arrayJSON;

    }




    public ArrayList<Card> ExtraerCartasNeutrales() {
        try {
            for (int i = 0; i < arrayJSON.length(); i++) {

                JSONObject cartaObtenida = arrayJSON.getJSONObject(i);               //extraemos CADA OBJETO(carta) del json en bucle
                String cardClass = cartaObtenida.getString("cardClass");      //extraemos la CLASE lo primero, para tomar solo neutrales



                //SOLO SI ES NEUTRAL,extraeremos el resto de la info de la carta y la añadiremos a la lista.
                if(cardClass.equals("NEUTRAL")) {

                    String id = cartaObtenida.getString("id");
                    String name = cartaObtenida.getString("name");
                    int cost;
                    try {
                        cost = cartaObtenida.getInt("cost");
                    } catch (Exception e) {
                        cost = 0;
                    }
                    String text;
                    try {
                        text = cartaObtenida.getString("text");
                    } catch (Exception e) {
                        text = "";
                    }
                    int attack;
                    int health;
                    try {
                        attack = cartaObtenida.getInt("attack");
                        health = cartaObtenida.getInt("health");
                    } catch (Exception e) {
                        attack = 0;
                        health = 0;
                    }
                    String ratity;
                    try {
                        ratity = cartaObtenida.getString("rarity");
                    } catch (Exception e) {
                        ratity = "";
                    }


                    //CREAMOS EL OBJETO CARTA Y LA AÑADIMOS A LA ARRAYLIST:

                    Card carta = new Card(id, name, text, cost, attack, health, cardClass, ratity);
                    cartasNeutrales.add(carta);

                }
            }
        } catch (JSONException ex) {
            Log.e("QueryUtils", "PROBLEMA CONSULTA JSON", ex);
        }
        return cartasNeutrales;
    }




    public ArrayList<Card> ExtraerCartasClase(String claseSeleccionada) {
        try {
            for (int i = 0; i < arrayJSON.length(); i++) {

                JSONObject cartaObtenida = arrayJSON.getJSONObject(i);               //extraemos CADA OBJETO(carta) del json en bucle
                String cardClass = cartaObtenida.getString("cardClass");      //extraemos la CLASE lo primero, para tomar solo neutrales


                //SOLO SI ES LA CLASE SELECCIONADA POR PARAMETRO,extraeremos el resto de la info de la carta y la añadiremos a la lista.
                if(cardClass.equals(claseSeleccionada)) {

                    String id = cartaObtenida.getString("id");
                    String name = cartaObtenida.getString("name");
                    int cost;
                    try {
                        cost = cartaObtenida.getInt("cost");
                    } catch (Exception e) {
                        cost = 0;
                    }
                    String text;
                    try {
                        text = cartaObtenida.getString("text");
                    } catch (Exception e) {
                        text = "";
                    }
                    int attack;
                    int health;
                    try {
                        attack = cartaObtenida.getInt("attack");
                        health = cartaObtenida.getInt("health");
                    } catch (Exception e) {
                        attack = 0;
                        health = 0;
                    }
                    String ratity;
                    try {
                        ratity = cartaObtenida.getString("ratity");
                    } catch (Exception e) {
                        ratity = "";
                    }


                    //CREAMOS EL OBJETO CARTA Y LA AÑADIMOS A LA ARRAYLIST:

                    Card carta = new Card(id, name, text, cost, attack, health, cardClass, ratity);
                    cartasClase.add(carta);

                }
            }
        } catch (JSONException ex) {
            Log.e("QueryUtils", "PROBLEMA CONSULTA JSON", ex);
        }

        return cartasClase;
    }



    public ArrayList<Card> getCartasNeutrales() {
        return cartasNeutrales;
    }
    public ArrayList<Card> getCartasClase() {
        return cartasClase;
    }
}
