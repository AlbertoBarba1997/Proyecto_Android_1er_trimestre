package com.example.android.proyecto.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.proyecto.ADAPTERS.ListAdapter;
import com.example.android.proyecto.AppDataBase.DataBaseRoom;
import com.example.android.proyecto.OpcionesActivity;
import com.example.android.proyecto.R;
import com.example.android.proyecto.ViewModel.CardViewModel;
import com.example.android.proyecto.ViewModel.DeckViewModel;
import com.example.android.proyecto.models.Card;
import com.example.android.proyecto.models.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListFragment extends Fragment {


    //Lista cartas y la clase a la que pertenecen, obtenidas por setter desde el activity.
    ArrayList <Card> cartasIntroducidas;
    String claseMazo;
    Deck deck=null;

    //Adapter y RecyclerView
    ListAdapter adapter;
    //////////////////////////////////////////////////////ListFragment.OnListo callback1;
    RecyclerView rvLista;
    RecyclerView.LayoutManager layoutManager;

    //Botones
    Button botonAtras;
    Button botonListo;

    //Base Datos
    DataBaseRoom dbRoom;
    CardViewModel modelCard;
    DeckViewModel modelDeck;
    //DcViewModel modelDC;
    long idDeck=-1;


    public ListFragment(){

    }
    public void setClaseMazo(String claseMazo) {this.claseMazo = claseMazo;}
    public void setCartasIntroducidas(ArrayList<Card> cartasIntroducidas) {this.cartasIntroducidas = cartasIntroducidas;}
    public void setDeck(Deck deck) {this.deck = deck;}
    public void setIdDeck(long idDeck) {this.idDeck = idDeck;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_list,container,false);

        System.out.println("CLASE MAZO:"+claseMazo);

        rvLista=(RecyclerView) view.findViewById(R.id.recyclerViewLista);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvLista.setLayoutManager(layoutManager);

        botonAtras=(Button) view.findViewById(R.id.BotonAtras);
        botonListo=(Button) view.findViewById(R.id.BotonListo);

        dbRoom=DataBaseRoom.getInstance(getContext());  ////////////////GETCONTEXT O GETACTIVITY?
        modelCard= ViewModelProviders.of(getActivity()).get(CardViewModel.class);
        modelDeck= ViewModelProviders.of(getActivity()).get(DeckViewModel.class);
        //modelDC= ViewModelProviders.of(getActivity()).get(DcViewModel.class);




        botonListo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se obtiene el numero del deck al que corresponden en caso de no haber sido pasado por parametros
                if(cartasIntroducidas.size()<1){
                    Toast.makeText(getContext(), "Introduce al menos 1 carta", Toast.LENGTH_SHORT).show();
                }else {
                    if (idDeck == -1) {
                        idDeck = deck.getId();
                    }
                    System.out.println("EL ID DEL DECK CREADO ES:" + idDeck);
                    //Asignar Numero Deck al que corresponden las cartas antes de introducirlas en la bd.
                    cartasIntroducidas = AsignarNDeck(cartasIntroducidas);
                    for (int i = 0; i < cartasIntroducidas.size(); i++) {
                        Card carta = cartasIntroducidas.get(i);
                        System.out.println("Introducciendo..." + carta.getName());
                        modelCard.addCard(carta);
                    }
                    Toast.makeText(getContext(), "INSERTADAS en el mazo nº "+ idDeck, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), OpcionesActivity.class);
                    startActivity(intent);
                }

            }
        });



        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OpcionesActivity.class);
                startActivity(intent);

            }
        });







        return view;
    }


    //OBTENER LAS CARTAS ENVIADAS DESDE EL OTRO FRAGMENT Y ADAPTARLAS AL RECYCLER.
    public void renderCard(Card carta, String claseEscogida){

        claseMazo=claseEscogida;

        /// CREAR DECK BD(si no nos lo pasan por parametro ya creado)
        if(deck==null){
            deck=new Deck(claseMazo);
            modelDeck.addDeck(deck);
            //idDeck=modelDeck.getIdDeck();

        }/*else{
            idDeck=deck.getId();
        }*/

        //Lega la carta desde el intent
        System.out.println(carta.toString());  //(comprobación)

        //INTRODUCIR LA CARTA EN LA LISTA:
        cartasIntroducidas.add(carta);

        //Ordenar:
        cartasIntroducidas=OrdenarPorCoste(cartasIntroducidas);

        //Comprobar Dobles
        cartasIntroducidas=ComprobarDobles(cartasIntroducidas);

        //Comprobar Legendarias(no se puede introducir mas de 1)
        cartasIntroducidas=ComprobarLegendarias(cartasIntroducidas);

        //Comprobar Maximo
        cartasIntroducidas=ComprobarMaximo(cartasIntroducidas);






        //ADAPTER / RECICLER
        adapter=new ListAdapter(cartasIntroducidas, R.layout.item_row_lista, getActivity(), new ListAdapter.OnClickListener() {
            @Override


            public void onClick(Card carta, int pos) {
            /// ELIMINAR LA CARTA EN LA POSICION INDICADA y recargar adapter
                if(carta.getDoble()){
                    carta.setDoble(false);                  //Si hay 2 iguales introducidas (booleano doble=true), solo quitara una volviento el doble=false.
                }
                else {
                    cartasIntroducidas.remove(pos);         //Si no la eliminará
                }
                rvLista.setAdapter(adapter);


            }
        });

        rvLista.setAdapter(adapter);



    }












    //FUNCIONES CONTROL ARRAY (Orden, dobles...)

    public ArrayList<Card> OrdenarPorCoste(ArrayList<Card> cartas){
        Collections.sort(cartas, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                return new Integer(c1.getCost()).compareTo(new Integer(c2.getCost()));
            }
        });
        return cartas;
    }

    public ArrayList<Card> ComprobarDobles(ArrayList<Card> cartas){
        for(int i=0; i<cartas.size(); i++){
            Card carta1=cartas.get(i);
            String id1=carta1.getId();
            for(int h=0; h<cartas.size(); h++){
                Card carta2=cartas.get(h);
                String id2=carta2.getId();

                if(i!=h&&id1.equals(id2)){                                      //si NO son las mismas cartas las que compara Y son iguales, elimina la segunda carta y la primera le da el atributo doble.
                    carta1.setDoble(true);
                    cartas.remove(h);
                    System.out.println("DOBLE DETECTADO"); //(comprobación)
                }
            }
        }
        return cartas;
    }

    public ArrayList<Card> ComprobarLegendarias(ArrayList<Card> cartas){
        for(int i=0; i<cartas.size(); i++){
            Card carta1=cartas.get(i);
            String id1=carta1.getId();
            String ratity=carta1.getRatity();
            for(int h=0; h<cartas.size(); h++){
                Card carta2=cartas.get(h);
                String id2=carta2.getId();

                if(i!=h && id1.equals(id2) && ratity.equals("LEGENDARY")){
                    cartas.remove(h);
                    Toast.makeText(getContext(), R.string.LegendariaUnica, Toast.LENGTH_SHORT).show();
                }
            }
        }
        return cartas;
    }

    public ArrayList<Card> ComprobarMaximo(ArrayList<Card> cartas){

        int contador=0;
        boolean maximoSuperado=false;

        for(int i=0; i<cartas.size(); i++) {
            Card carta = cartas.get(i);
            if(carta.getDoble()){
                contador=contador+2;
            }else{
                contador++;

            }


            if(contador>30){
                maximoSuperado=true;
            }
            if(maximoSuperado){
                Toast.makeText(getContext(), R.string.MaximoSuperado, Toast.LENGTH_SHORT).show();
                cartas.remove(i);
            }
        }

        return cartas;

    }

    public  ArrayList<Card> AsignarNDeck(ArrayList<Card> cartas){

        for(int i=0;i<cartas.size();i++) {
            cartas.get(i).setIdDeck(idDeck);
        }

        return cartas;
    }











}