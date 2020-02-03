package com.example.android.proyecto.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.proyecto.ADAPTERS.CardAdapter;
import com.example.android.proyecto.JSON.JsonConsulta;
import com.example.android.proyecto.R;
import com.example.android.proyecto.models.Card;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.android.proyecto.R.string.Druida;

public class CardsFragment extends Fragment {

    //Clase seleccionada(recibida por intent):
    private String clase;

    //RecyclerViews:
    RecyclerView rvCartasNeutrales;
    RecyclerView rvCartasClase;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager layoutManager2;

    //Listas de cartas:
    ArrayList<Card> cartasNeutrales=new ArrayList<>();
    ArrayList<Card> cartasClase=new ArrayList<>();

    //JSON:
    public static final String URL="https://api.hearthstonejson.com/v1/25770/esES/cards.collectible.json";
    JsonConsulta consulta;

    //Adapters:
    CardAdapter adapterNeutrales;
    CardAdapter adapterClase;
    OnCardSelected callback;




    public CardsFragment(){

    }

    public void setClase(String clase) {
        this.clase = clase;
        Toast.makeText(getContext(), clase, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_cards,container,false);

        //RECYCLER VIEW
        rvCartasNeutrales=view.findViewById(R.id.recyclerViewCartasNeutrales);
        rvCartasClase=view.findViewById(R.id.recyclerViewCartasDeClase);

        //POSIBLE PROBLEMA
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvCartasClase.setLayoutManager(layoutManager2);
        rvCartasNeutrales.setLayoutManager(layoutManager);









        ///// CONSULTA JSON VOLLEY /////

        RequestQueue queue=Volley.newRequestQueue(this.getContext());
        JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, URL,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                consulta =new JsonConsulta(response);                       //A través de la consulta, obtendremos una respuesta en forma de JSONArray, que será la utilizada para extraer las cartas "serializadas en objetos.

                    //EXTRAER CARTAS neutrales y de clase:
                cartasNeutrales=consulta.ExtraerCartasNeutrales();
                cartasClase=consulta.ExtraerCartasClase(clase);

                    //ORDENARLAS:
                cartasNeutrales=OrdenarPorCoste(cartasNeutrales);
                cartasClase=OrdenarPorCoste(cartasClase);

                    //COMPROBAR DOBLES:
                cartasNeutrales=ComprobarDobles(cartasNeutrales);
                cartasClase=ComprobarDobles(cartasClase);










                ///// ADAPTERS /////

                adapterNeutrales=new CardAdapter(cartasNeutrales, R.layout.item_carta, getActivity(), new CardAdapter.OnClickListener() {
                    @Override
                    public void onClick(Card carta, int pos) {
                        callback.clickCard(carta);
                    }
                });


                adapterClase=new CardAdapter(cartasClase, R.layout.item_carta, getActivity(), new CardAdapter.OnClickListener() {
                    @Override
                    public void onClick(Card carta, int pos) {
                        callback.clickCard(carta);
                    }
                });

                rvCartasNeutrales.setAdapter(adapterNeutrales);
                rvCartasClase.setAdapter(adapterClase);




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", error.toString());
            }
        });




        queue.add(request);


        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            callback=(OnCardSelected)context;
        }catch(ClassCastException ex){
            throw new ClassCastException(context.toString()+" debería implementar la interfaz OnCardSelected");
        }
    }

    public interface OnCardSelected{
        public void clickCard(Card carta);
    }




    //FUNCIONES CONTROL ARRAY (Orden, dobles...):




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

                if(i!=h&&id1.equals(id2)){
                    carta1.setDoble(true);
                    cartas.remove(h);
                    System.out.println("DOBLE DETECTADO"); //(comprobación)
                }

            }
        }
        return cartas;

    }







}
